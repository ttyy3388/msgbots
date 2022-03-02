package org.beuwi.msgbots.view.gui.control;

import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;

import org.beuwi.msgbots.view.gui.layout.WebPane;
import org.beuwi.msgbots.utils.ResourceUtils;

import java.util.HashMap;
import java.util.Map;

// Sun 내부에 있는 "net...sun..WebPage"와 다름
public class WebPage extends WebPane {
	// [WebPage]는 하나의 페이지(HTML)에 하나만 존재할 수 있음
	private static final Map<String, WebPage> created = new HashMap<>();

	private final WebEngine engine;
	private final Worker worker;

	/* public Page() {
		this(null);
	} */

	// Not Null
	private WebPage(String name) {
		engine = getWebEngine();
		worker = getLoadWorker();

		loadHtml(ResourceUtils.getHtml(name));

		// JLINK 패키징 시 경로 에러가 나므로 사용 안함
		/* engine.setUserStyleSheetLocation(
			ResourceUtils.getWebStyle(theme)
		); */
		addStyleClass("web-page");
	}

	public static WebPage getPage(String name) {
		if (!created.containsKey(name)) {
			created.put(name, new WebPage(name));
		}
		return created.get(name);
	}
}