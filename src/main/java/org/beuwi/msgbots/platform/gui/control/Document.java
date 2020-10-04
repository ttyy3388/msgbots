package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

// Use At Document Tab
public class Document extends StackPane
{
	public static final String DEFAULT_STYLE_CLASS = "document";

	private final WebView main = new WebView();
	private final WebEngine engine = main.getEngine();

	public Document()
	{
		getChildren().add(main);
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
