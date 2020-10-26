package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class Document extends StackPane
{
	private static final String DEFAULT_STYLE_CLASS = "document";

	private final WebView main = new WebView();
	private final WebEngine engine = main.getEngine();

	public Document()
	{
		// setContent(main);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public Document setLink(String link)
	{
		engine.load(link);

		return this;
	}

	public Document setContent(String html)
	{
		engine.loadContent(html);

		return this;
	}
}
