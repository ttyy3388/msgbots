package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

public class ShowSideBarAction
{
	private static AnchorPane anpSideBar = null;

	public static void initAction()
	{
		anpSideBar = SideBarManager.getComponent();
	}

	public static void update(int index)
	{
		anpSideBar.getChildren().get(index).setDisable(true);
		anpSideBar.getChildren().get(index).setVisible(true);
	}
}