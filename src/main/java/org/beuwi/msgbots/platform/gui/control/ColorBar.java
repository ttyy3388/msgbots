package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.layout.Pane;

public class ColorBar extends Pane
{
	private static final String DEFAULT_STYLE_CLASS = "color-bar";
	private static final int DEFAULT_PREF_WIDTH = 5;

	public enum Type
	{
		EVENT, ERROR, WARNING // DEBUG
	}

	public ColorBar()
	{
		// Default
		setType(Type.EVENT);

		setPrefWidth(DEFAULT_PREF_WIDTH);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setType(Type type)
	{
		setColor
		(
			switch (type)
					{
				case EVENT -> "#0F82E6";
				case ERROR -> "#EA3C53";
				case WARNING -> "#E05A00";
			}
		);
	}

	public void setColor(String color)
	{
		setStyle("-fx-background-color:" + color);
	}
}