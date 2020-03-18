package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.managers.ActivityBarManager;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

public class ChangeSideBarAction
{
	private static AnchorPane anpActivityBar = null;
	private static AnchorPane anpSideBar = null;

	public static void initAction()
	{
		anpActivityBar = ActivityBarManager.getComponent();
		anpSideBar = SideBarManager.getComponent();
	}

	public static void update(int index)
	{
		switch (index)
		{
			// Projects Part
			case 0 :
				anpSideBar.getChildren().get(0).setVisible(true);
				break;

			// Simulation Part
			case 1 :
				anpSideBar.getChildren().get(1).setVisible(true);
				break;
		}
	}
}