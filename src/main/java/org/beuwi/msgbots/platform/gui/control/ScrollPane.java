package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

@DefaultProperty("content")
public class ScrollPane extends javafx.scene.control.ScrollPane
{
	public static final String DEFAULT_STYLE_CLASS = "scroll-pane";

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
			setHvalue(getHvalue() - (event.getDeltaX() * DEFAULT_SCROLL_SPEED));
			setVvalue(getVvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
		});

		setHvalue(1.0d);
		setVvalue(1.0d);

		setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

		// setFitToWidth(true);
		// setFitToHeight(true);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
