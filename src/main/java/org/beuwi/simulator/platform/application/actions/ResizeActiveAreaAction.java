package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.parts.activitybar.ActiveAreaManager;

public class ResizeActiveAreaAction
{
	private static AnchorPane anpActiveArea = null;

	public static void initialize()
	{
		anpActiveArea = ActiveAreaManager.getAnchorPane();
	}

	public static void update(MouseEvent event)
	{
		double sceneX = event.getSceneX();

		// 이 구역에서 드래그 시 깜빡임 현상이 있어서 제외
		if (!(sceneX >= -5 && sceneX <= 5))
		{
			anpActiveArea.setPrefWidth(sceneX);
		}
	}
}