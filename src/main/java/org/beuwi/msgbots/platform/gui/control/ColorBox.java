package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class ColorBox extends HBox<Pane>
{
	private static final String DEFAULT_STYLE_CLASS = "color-box";

	private final StackPane pane = new StackPane();
	private final ColorBar bar = new ColorBar();

	{
		HBox.setHgrow(pane, Priority.ALWAYS);
	}

	public ColorBox()
	{
		addItem(bar, pane);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public Node getContent()
	{
		return pane.getContent();
	}

	public void setContent(Node content)
	{
		this.pane.setContent(content);
	}

	public void setType(ColorBar.Type type)
	{
		bar.setType(type);
	}

	public ColorBar getColorBar()
	{
		return bar;
	}
}