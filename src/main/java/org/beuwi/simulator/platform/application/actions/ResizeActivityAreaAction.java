package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.managers.ActivityAreaManager;

public class ResizeActivityAreaAction
{
	private static AnchorPane anpActivityArea = null;

	public static void initAction()
	{
	    anpActivityArea = ActivityAreaManager.getComponent();
	}

	public static void update(MouseEvent event)
	{
		double sceneX = event.getSceneX();

		// 이 구역에서 드래그 시 깜빡임 현상이 있어서 이 구역 제외
		if (!(sceneX >= -5 && sceneX <= 5))
		{
			anpActivityArea.setPrefWidth(sceneX);
		}
	}
}