package org.beuwi.simulator.platform.ui.components;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;

public class IButton extends Button
{
	final public static int DEFAULT = 0;
	final public static int ACTION  = 1;

	public IntegerProperty property = new SimpleIntegerProperty(1);

	{
		this.getStyleClass().add("ifx-button");
	}

	public IButton()
	{
		this(0);
	}

	public IButton(int type)
	{
		this.property.set(type);

		this.property.addListener((observable, oldValue, newValue) ->
		{
			switch (property.get())
			{
				case DEFAULT :

					this.getStyleClass().add("ifx-button-default");

					break;

				case ACTION  :

					if (this.getStyleClass().contains("ifx-button-default"))
					{
						this.getStyleClass().remove("ifx-button-default");
					}

					this.getStyleClass().add("ifx-button-action");

					break;
			}
		});
	}

	public void setButtonType(int type)
	{
		this.property.set(type);
	}

	public int getButtonType()
	{
		return this.property.get();
	}
}