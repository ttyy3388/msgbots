package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Button;

public class IMenuButton extends Button
{
	{
		this.getStyleClass().add("ifx-menu-button");
	}

	public void setMenu(IContextMenu menu)
	{
		menu.setNode(this);
	}
}
