package org.beuwi.simulator.platform.application.parts.toolbar;

import javafx.scene.layout.AnchorPane;

public class ToolBarManager
{
	private static AnchorPane component = null;

	public static void initialize(AnchorPane anpMenuBar)
	{
		component = anpMenuBar;
	}

	public static AnchorPane getComponent()
	{
		return component;
	}
}
