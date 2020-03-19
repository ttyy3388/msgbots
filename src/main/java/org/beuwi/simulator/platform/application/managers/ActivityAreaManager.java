package org.beuwi.simulator.platform.application.managers;

import javafx.scene.layout.AnchorPane;

public class ActivityAreaManager
{
	private static AnchorPane component = null;

	public static void initManager(AnchorPane anpActivityBar)
	{
		component = anpActivityBar;
	}

	public static AnchorPane getComponent()
	{
		return component;
	}
}