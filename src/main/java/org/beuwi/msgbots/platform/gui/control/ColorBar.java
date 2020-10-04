package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorBar extends Pane
{
	public static final String DEFAULT_STYLE_CLASS = "color-bar";

	public static final int DEFAULT_PREF_WIDTH = 5;

	public enum Type
	{
		EVENT, ERROR, WARNING // DEBUG
	}

	public ColorBar()
	{
		setPrefWidth(DEFAULT_PREF_WIDTH);

		setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(15, 0, 0, 15, false), Insets.EMPTY)));

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setType(ColorBar.Type type)
	{
		setColor
		(
			Color.valueOf
			(
				switch (type)
				{
					case EVENT -> "#187BCD";
					case ERROR -> "#EA3C53";
					case WARNING -> "#E05A00";
				}
			)
		);
	}

	// Log Type Compat
	public void setType(Log.Type type)
	{
		setType
		(
			switch (type)
		    {
			    case EVENT -> Type.EVENT;
				case ERROR -> Type.ERROR;
				case DEBUG -> Type.WARNING;
			}
		);
	}

	public void setColor(Color color)
	{
		setBackground(new Background(new BackgroundFill(color, new CornerRadii(5, 0, 0, 5, false), Insets.EMPTY)));
	}
}