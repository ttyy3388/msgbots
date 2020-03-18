package org.beuwi.simulator.platform.application.managers;

import javafx.scene.layout.AnchorPane;

public class SideBarManager
{
	private static AnchorPane anpSideBar = null;

	public static void setSideBar(AnchorPane anpSideBar)
	{
        SideBarManager.anpSideBar = anpSideBar;
	}

	public static AnchorPane getSideBar()
    {
        return anpSideBar;
    }

    public static void hideSideBar()
	{

	}

	public static void showSideBar()
	{

	}
}