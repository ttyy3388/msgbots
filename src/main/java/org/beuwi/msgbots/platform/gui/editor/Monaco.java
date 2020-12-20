package org.beuwi.msgbots.platform.gui.editor;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.app.action.OpenBrowserAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;
import org.beuwi.msgbots.platform.gui.control.Page;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;
import org.beuwi.msgbots.platform.util.ResourceUtils;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class Monaco {
	private static final String DEFAULT_RESOURCE_LOCATION = ResourceUtils.getURL("/monaco/index.html");

	private final StringProperty language = new SimpleStringProperty(null);
	private final StringProperty theme = new SimpleStringProperty(null);
	private final StringProperty text = new SimpleStringProperty(null);

	// Monaco Window
	private JSObject window = null;

	// Monaco Editor
	private JSObject editor = null;

	private final WebView view = new WebView();
	private final WebEngine engine;
	private final Worker worker;

	// Content Change Listener : 전역 변수로 해야 사라지지 않고 남아있음
	private final JFunction listener = new JFunction(args -> {
		String text = (String) editor.call("getValue");
		if (text != null) {
			setText(text);
		}
		return null;
	});

	public Monaco() {
		engine = view.getEngine();
		worker = engine.getLoadWorker();

		engine.load(DEFAULT_RESOURCE_LOCATION);

		engine.setOnAlert(event ->
		{
			System.out.println(event.getData());
		});

		worker.stateProperty().addListener(event -> {
			State state = worker.getState();

			if (state.equals(State.SUCCEEDED)) {
				window = (JSObject) engine.executeScript("window");

				AtomicBoolean jsdone = new AtomicBoolean(false);
				AtomicInteger attempts = new AtomicInteger();

				Thread thread = new Thread(() -> {
					while (!jsdone.get()) {
						try {
							Thread.sleep(500);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}

						// check if JS execution is done.
						Platform.runLater(() -> {
							Object editorView = window.call("getEditorView");

							if (editorView instanceof JSObject) {
								editor = (JSObject) editorView;

								editor.call("setValue", getText());
								window.setMember("contentChangeListener", listener);

								engine.executeScript("monaco.editor.setTheme('" + getTheme() + "')");
								engine.executeScript("monaco.editor.setModelLanguage(editorView.getModel(),'" + getLanguage() + "')");

								language.addListener(change -> {
									engine.executeScript("monaco.editor.setModelLanguage(editorView.getModel(),'" + getLanguage() + "')");
								});

								theme.addListener(change -> {
									engine.executeScript("monaco.editor.setTheme('" + getTheme() + "')");
								});

								jsdone.set(true);
							}
						});

						if (attempts.getAndIncrement() > 10) {
							throw new RuntimeException("Cannot initialize editor (JS execution not complete). Max number of attempts reached.");
						}
					}
				});

				thread.start();
			}
		});
	}

	public void cut() {
		engine.executeScript("editor.replaceSelection(\"\");");
		CopyStringAction.execute(getSelectedText());
	}

	public void copy() {
		CopyStringAction.execute(getSelectedText());
	}

	public void paste() {
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		String content = (String) clipboard.getContent(DataFormat.PLAIN_TEXT);
		engine.executeScript(String.format("editorView.replaceSelection(\"%s\");", content));
	}

	private void execute(String action) {
		if (engine == null) {
			return ;
		}
		engine.executeScript(action);
		// engine.executeScript("monaco.editor.execCommand(" + action + ")");
	}

	protected void setText(String text) {
		this.text.set(text);
	}

	protected void setTheme(String theme) {
		this.theme.set(theme);
	}

	protected void setLanguage(String language) {
		this.language.set(language);
	}

	/* public void scrollToLine(int line) {
		editor.call("revealLine", line);
	}

	public void scrollToLineCenter(int line) {
		editor.call("revealLineInCenter", line);
	} */

	protected WebView getView() {
		return view;
	}

	protected String getText() {
		return text.get();
	}

	protected String getTheme() {
		return theme.get();
	}

	protected String getLanguage() {
		return language.get();
	}

	public String getSelectedText() {
		return (String) engine.executeScript("editorView.getModel().getValueInRange(editorView.getSelection())");
	}
}
