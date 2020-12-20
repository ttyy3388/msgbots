package org.beuwi.msgbots.platform.gui.editor;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import javafx.scene.input.KeyEvent;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

import java.io.File;

public final class Editor extends StackPanel
{
	private static final String DEFAULT_STYLE_CLASS = "editor";

	private final Monaco monaco = new Monaco();

	private final FileProperty file = new FileProperty(change -> {
		File file = this.file.get();

		if (file != null) {
			setText(FileManager.read(file));

			/* FileManager.link(file, () ->
			{
				setText(file);
			}); */

			addEventFilter(KeyEvent.KEY_PRESSED, event -> {
				if (event.isControlDown()) {

					switch (event.getCode()) {
						// case S :
						// FileManager.save(file, getText()); break;
						// case S : save(); break;
						case C :
							monaco.copy(); break;
						// case Z : redo(); break;
						// case Y : undo(); break;
					}
				}
			});
		}
	});

	public Editor() {
		this(null);
	}

	public Editor(File file) {
		if (file != null) {
			setFile(file);
		}

		setItem(monaco.getView());
		setTheme("vs-dark");
		setLanguage("javascript");
		setStyleClass("editor");
	}

	/* private void execute(String action) {
		monaco.execute(action);
	} */

	public void setFile(File file) {
		this.file.set(file);
	}

	private void setText(String text) {
		monaco.setText(text);
	}

	public void setTheme(String theme) {
		monaco.setTheme(theme);
	}

	public void setLanguage(String language) {
		monaco.setLanguage(language);
	}

	public File getFile() {
		return file.get();
	}

	public String getText() {
		return monaco.getText();
	}

	public String getTheme() {
		return monaco.getTheme();
	}

	public String getLanguage() {
		return monaco.getLanguage();
	}

	private class FileProperty extends SimpleObjectProperty<File> {
		public FileProperty(InvalidationListener listener) {
			addListener(listener);
		}
	}
}
