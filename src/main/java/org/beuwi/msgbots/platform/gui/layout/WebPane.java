package org.beuwi.msgbots.platform.gui.layout;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Worker;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import netscape.javascript.JSObject;

import org.beuwi.msgbots.action.OpenBrowserAction;
import org.beuwi.msgbots.keyboard.KeyBinding;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.utils.ResourceUtils;
import org.beuwi.msgbots.setting.GlobalSettings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

// JavaFX에서 WebView, WebEngine.. 등등을 다 final로 선언해놔서 이렇게 구현함
public class WebPane extends StackPane {
	// 웹에서도 프로그램 내부에 접근할 수 있도록 함
	private final Bridge bridge = new Bridge();
	public static class Bridge {
		public void execute(String action) {
			String clazz = action.split("\\(")[0];
			String param = action.split("\\(")[1].split("\\)")[0];

			switch (clazz) {
				case "open.dialog.action" :
					switch (param) {
						// case "create.bot.dialog": OpenDialogBoxAction.getInstance().execute(new CreateBotDialog()); break;
						// case "import.bot.dialog": OpenDialogBoxAction.getInstance().execute(new ImportBotDialog()); break;
					}
					break;
				case "open.browser.action" : OpenBrowserAction.getInstance().execute(param); break;
			}
		}
	}

	private final WebView browser = new WebView();
	private final WebEngine engine;
	private final Worker worker;

	private final BooleanProperty loadedProperty = new SimpleBooleanProperty(false);
	private final String theme = GlobalSettings.getString("program.colorTheme");

	public WebPane() {
		engine = browser.getEngine();
		worker = engine.getLoadWorker();

		// engine.setUserAgent("");
		// browser.setContextMenuEnabled(false);
		engine.setJavaScriptEnabled(true);
		/* browser.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (KeyCode.F12.equals(event.getCode())) {
				engine.executeScript("if (!document.getElementById('FirebugLite'))" +
                        "{E = document['createElement' + 'NS'] && " +
                        "document.documentElement.namespaceURI;E = E ? " +
                        "document['createElement' + 'NS'](E, 'script') : " +
                        "document['createElement']('script');E['setAttribute']('id', 'FirebugLite');" +
                        "E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');" +
                        "E['setAttribute']('FirebugLite', '4');" +
                        "(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);" +
                        "E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}");
			}
		}); */


		// 테마 스타일 적용, 여기서 테마는 프로그램 테마임(모나코 테마랑 다름!)
		// 따라서 시작할 때 한 번만 적용되며 구분을 위해 따로 빼서 구현했음
		worker.stateProperty().addListener(event -> {
			Worker.State state = worker.getState();
			if (state.equals(Worker.State.SUCCEEDED)) {
				AtomicBoolean jsdone = new AtomicBoolean(false);
				AtomicInteger attempts = new AtomicInteger(0);

				Thread thread = new Thread(() -> {
					while (!jsdone.get()) {
						try {
							Thread.sleep(500);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}

						Platform.runLater(() -> {
							Object object = engine.executeScript("window");
							Document document = engine.getDocument();
							if (object instanceof JSObject && document != null) {
								jsdone.set(true);
								Element element = document.getDocumentElement();
								// 테마 스타일 적용
								Element style = document.createElement("style");
								// 상대 경로를 적용시킬 수 없으므로 기본 스타일을 읽어와서 상단에 추가
								InputStream stream = ResourceUtils.getStream("/webpage/style/" + theme + ".css");
								String data = FileManager.read(stream);
								style.setTextContent(data);
								// element.setNodeValue("@import url(\"../style/dark.css\");");
								/* element.appendChild(
										document.createTextNode("@import url(\"../style/dark.css\");")); */
								element.appendChild(style);

								/* style.appendChild(content);
								document.getElementsByTagName("style")
										.item(0).appendChild(style); */

								((JSObject) object).setMember("program", bridge);
								// 해당 구역(스타일 적용)이 가장 마지막에 되므로 로딩이 완료된거로 처리함
								loadComplete();
							}
						});

						if (attempts.getAndIncrement() > 10) {
							throw new RuntimeException("Cannot apply style (JS execution not complete). Max number of attempts reached.");
						}
					}
				});
				thread.start();
			}
		});

		// 로딩이 다 돼야 보이도록
		browser.setVisible(false);

		getChildren().setAll(browser);
		getStyleClass().add("web-page");
	}

	public void loadHtml(String url) {
		engine.load(url);
	}

	// 웹 뷰 로딩 성공 시
	private void loadComplete() {
		setLoaded(true);
		browser.setVisible(true);
	}

	public WebView getWebView() {
		return browser;
	}
	public WebEngine getWebEngine() {
		return engine;
	}
	public Worker getLoadWorker(){
		return worker;
	}

	/* public String getTheme() {
		return theme;
	} */

	private void setLoaded(boolean value) {
		loadedProperty.set(value);
	}
	public boolean isLoaded() {
		return loadedProperty.get();
	}
	public ReadOnlyBooleanProperty loadedProperty() {
		return loadedProperty;
	}
}
