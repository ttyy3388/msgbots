package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.parts.SideBarPart;

public class ChangeSideBarAction
{
	private static AnchorPane anpExplorerTab;
	private static AnchorPane anpDebugTab;

	public static void initialize()
	{
		anpExplorerTab = (AnchorPane) SideBarPart.getNameSpace().get("anpExplorerTab");
		anpDebugTab    = (AnchorPane) SideBarPart.getNameSpace().get("anpDebugTab");
	}

	public static void update(int index)
	{
		switch (index)
		{
			// Explorer Tab
			case 0 :
				anpExplorerTab.setDisable(false);
				anpExplorerTab.setVisible(true);
				anpDebugTab.setDisable(true);
				anpDebugTab.setVisible(false);
				break;

			// Debug Tab
			case 1 :
				anpDebugTab.setDisable(false);
				anpDebugTab.setVisible(true);
				anpExplorerTab.setDisable(true);
				anpExplorerTab.setVisible(false);
				break;
		}
	}
}