package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.parts.ActiveAreaPart;

public class ResizeSideBarAction
{
	private static AnchorPane anpActiveArea = null;

	public static void initialize()
	{
		anpActiveArea = ActiveAreaPart.getRoot();
	}

	public static void update(MouseEvent event)
	{
		double sceneX = event.getSceneX();

		// 이 부분에서 드래그 시 깜빡이는 문제점 방지
		if (!(sceneX >= -5 && sceneX <= 5))
		{
			if (HideSideBarAction.isHided())
			{
				if (150 <= sceneX)
				{
					HideSideBarAction.update(false);
				}
			}
			else
			{
				// 200 안에서는 사이즈 변경 X
				if (sceneX <= 220)
				{
					// 150 안으로 드래그 시 숨김
					if (sceneX <= 150)
					{
						HideSideBarAction.update(true);
					}
				}
				else
				{
					anpActiveArea.setPrefWidth(sceneX);
				}
			}
		}
	}
}