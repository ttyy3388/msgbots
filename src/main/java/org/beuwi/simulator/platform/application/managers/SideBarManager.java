package org.beuwi.simulator.platform.application.managers;

import javafx.scene.layout.AnchorPane;

public class SideBarManager
{
	private static AnchorPane component = null;
	// private static boolean[] status = {false, false};

	public static void initManager(AnchorPane anpSideBar)
	{
		component = anpSideBar;
	}

	public static AnchorPane getComponent()
    {
        return component;
    }
}