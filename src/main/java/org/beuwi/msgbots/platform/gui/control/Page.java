package org.beuwi.msgbots.platform.gui.control;

import javafx.concurrent.Worker;
import javafx.concurrent.Worker.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import netscape.javascript.JSObject;

import org.beuwi.msgbots.platform.app.action.OpenBrowserAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.app.view.dialogs.ImportBotDialog;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.setting.GlobalSettings;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Text;

public class Page extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "page";

	private final Actions actions = new Actions();
	public class Actions {
		public void execute(String action) {
			String clazz = action.split("\\(")[0];
			String param = action.split("\\(")[1].split("\\)")[0];

			switch (clazz) {
				case "open.dialog.action" :
					switch (param) {
						case "create.bot.dialog": OpenDialogBoxAction.execute(new CreateBotDialog()); break;
						case "import.bot.dialog": OpenDialogBoxAction.execute(new ImportBotDialog()); break;
					}
					break;
				case "open.browser.action" : OpenBrowserAction.execute(param); break;
			}
		}
	}

	private final WebView browser = new WebView();
	private final WebEngine engine;
	private final Worker worker;

	/* public Page() {
		this(null);
	} */

	// Not Null
	public Page(String name) {
		engine = browser.getEngine();
		worker = engine.getLoadWorker();

		String theme = GlobalSettings.getString("program:color_theme");

		engine.load(ResourceUtils.getHtml(name));
		// engine.setUserAgent("");
		engine.setJavaScriptEnabled(true);

		// JLINK 패키징 시 경로 에러가 나므로 사용 안함
		/* engine.setUserStyleSheetLocation(
			ResourceUtils.getWebStyle(theme)
		); */

		worker.stateProperty().addListener(change -> {
			State state = worker.getState();

			if (state.equals(State.SUCCEEDED)) {
				JSObject object = (JSObject) engine.executeScript("window");
				Document document = engine.getDocument();

				Element element = document.getDocumentElement();

				// 테마 스타일 적용
				Element style = document.createElement("style");
				style.setTextContent("@import url(\"../style/" + theme + ".css\");");
				// element.setNodeValue("@import url(\"../style/dark.css\");");
				/* element.appendChild(
						document.createTextNode("@import url(\"../style/dark.css\");")); */

				element.appendChild(style);

				/* style.appendChild(content);
				document.getElementsByTagName("style")
						.item(0).appendChild(style); */

				object.setMember("program", actions);
			}
		});

		getChildren().setAll(browser);
		getStyleClass().setAll("page");
	}
}