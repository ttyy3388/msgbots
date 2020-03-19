package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

public class ResizeSideBarAction
{
	private static AnchorPane anpSideBar = null;

	public static void initAction()
	{
		anpSideBar = SideBarManager.getComponent();
	}

	public static void update(MouseEvent event)
	{
		// 40 : Activity Bar Width
		double sceneX = event.getSceneX() - 40;

		// 이 구역에서 드래그 시 깜빡임 현상이 있어서 이 구역 제외
		if (!(sceneX >= -5 && sceneX <= 5))
		{
			// 50 : Editor Area Min Width
			// if (sceneX <= 50)
			{
				anpSideBar.setPrefWidth(sceneX);
			}
		}
	}
}