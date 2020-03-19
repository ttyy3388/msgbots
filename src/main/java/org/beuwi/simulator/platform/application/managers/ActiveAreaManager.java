package org.beuwi.simulator.platform.application.managers;

import javafx.scene.layout.HBox;

public class ActiveAreaManager
{
	private static HBox component = null;

	public static void initManager(HBox hoxActiveArea)
	{
		component = hoxActiveArea;
	}

	public static HBox getComponent()
	{
		return component;
	}
}