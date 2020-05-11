package org.beuwi.simulator.platform.ui.components;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class IButton extends Button
{
	{
		getStyleClass().add("ifx-button");
	}

	public static final int DEFAULT = 0;
	public static final int ACTION  = 1;

	public final IntegerProperty property = new SimpleIntegerProperty(1);

	public IButton()
	{
		this(DEFAULT);
	}

	public IButton(int type)
	{
		property.set(type);

		property.addListener((observable, oldValue, newValue) ->
		{
			switch (property.get())
			{
				case DEFAULT :

					getStyleClass().add("ifx-button-default");

					break;

				case ACTION  :

					if (getStyleClass().contains("ifx-button-default"))
					{
						getStyleClass().remove("ifx-button-default");
					}

					getStyleClass().add("ifx-button-action");

					break;
			}
		});
	}

	public void setMenu(IContextMenu menu)
	{
		menu.setNode(this);
	}

	public void setType(int type)
	{
		property.set(type);
	}

	public int getType()
	{
		return property.get();
	}
}