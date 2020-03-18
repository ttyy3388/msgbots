package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.managers.ActivityBarManager;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

public class ResizeSideBarAction
{
	private static AnchorPane anpActivityBar = null;
	private static AnchorPane anpSideBar = null;

	public static void initAction()
	{
	    anpActivityBar = ActivityBarManager.getComponent();
	    anpSideBar = SideBarManager.getComponent();
	}

	public static void update(MouseEvent event)
	{
		double sceneX = event.getSceneX();

		// 이 구역에서 드래그 시 깜빡임 현상이 있어서 이 구역 제외
		if (!(sceneX >= 35 && sceneX <= 45))
		{
			anpSideBar.setPrefWidth(sceneX - anpActivityBar.getPrefWidth());
		}
	}
}