package org.beuwi.simulator.platform.application.parts.activitybar;

import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class ActiveAreaManager
{
	private static AnchorPane anchorPane = null;
	private static TabPane component = null;

	public static void initialize(AnchorPane anpActiveArea, TabPane tapActiveArea)
	{
		anchorPane = anpActiveArea;
		component = tapActiveArea;
	}

	public static AnchorPane getAnchorPane()
	{
		return anchorPane;
	}

	public static TabPane getComponent()
	{
		return component;
	}
}