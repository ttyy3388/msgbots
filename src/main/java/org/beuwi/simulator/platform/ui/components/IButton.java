package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Button;

public class IButton extends Button
{
	final public int OK = 0;
	final public int NO = 1;

	{
		this.getStyleClass().add("ifx-button");
	}

	public IButton()
	{

	}
}