package org.beuwi.msgbots.program.gui.control;

import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;

import org.beuwi.msgbots.program.gui.layout.WebPane;
import org.beuwi.msgbots.utils.ResourceUtils;

// Sun 내부에 있는 "net...sun..WebPage"와 다름
public class WebPage extends WebPane {

	private final WebEngine engine;
	private final Worker worker;

	/* public Page() {
		this(null);
	} */

	// Not Null
	public WebPage(String name) {
		engine = getWebEngine();
		worker = getLoadWorker();

		loadHtml(ResourceUtils.getHtml(name));

		// JLINK 패키징 시 경로 에러가 나므로 사용 안함
		/* engine.setUserStyleSheetLocation(
			ResourceUtils.getWebStyle(theme)
		); */
		getStyleClass().add("web-page");
	}
}