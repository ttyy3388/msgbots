package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

public class ColorBox extends HBox<Pane>
{
    public static final String DEFAULT_STYLE_CLASS = "color-box";

    private final StackPane pane = new StackPane();
	private final ColorBar bar = new ColorBar();

	// private final Pane pane = new Pane();

    {
        HBox.setHgrow(pane, Priority.ALWAYS);
    }

	public ColorBox()
	{
	    getChildren().add(bar);
	    getChildren().add(pane);
        getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public Node getContent()
    {
        return pane.getChildren().get(0);
    }

	public void setContent(Node content)
	{
		this.pane.getChildren().setAll(content);
	}

	public void setType(ColorBar.Type type)
	{
		bar.setType(type);
	}

	public void setType(Log.Type type)
	{
		bar.setType(type);
	}

	public ColorBar getColorBar()
	{
		return bar;
	}
}