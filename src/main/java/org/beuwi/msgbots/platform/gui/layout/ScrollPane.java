package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.DefaultProperty;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

@DefaultProperty("content")
public class ScrollPane extends javafx.scene.control.ScrollPane
{
	private static final String DEFAULT_STYLE_CLASS = "scroll-pane";

	private static final double DEFAULT_SCROLL_SPEED = 0.005;

	public ScrollPane()
	{
		this(null);
	}

	public ScrollPane(Node node)
	{
		super(node);

		addEventFilter(ScrollEvent.SCROLL, event ->
		{
			setHvalue(getHvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
			setVvalue(getVvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
		});

		setHbarPolicy(ScrollBarPolicy.ALWAYS);
		setVbarPolicy(ScrollBarPolicy.ALWAYS);

		setFitToWidth(true);
		setFitToHeight(true);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}