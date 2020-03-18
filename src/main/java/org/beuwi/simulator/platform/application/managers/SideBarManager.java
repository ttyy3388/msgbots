package org.beuwi.simulator.platform.application.managers;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.HideSideBarAction;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;
import org.beuwi.simulator.platform.application.actions.ShowSideBarAction;

public class SideBarManager
{
	private static AnchorPane component = null;
	private static boolean[] status = {false, false};

	public static void initManager(AnchorPane anpSideBar)
	{
		component = anpSideBar;
	}

	public static void initActions()
	{
		HideSideBarAction.initAction();
		ResizeSideBarAction.initAction();
		ShowSideBarAction.initAction();
	}

	public static AnchorPane getComponent()
    {
        return component;
    }

    public static void setHided(int index, boolean hided)
	{
		status[index] = hided;
	}

    public static boolean isHided(int index)
	{
		return status[index];
	}
}