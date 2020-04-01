package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class ResizeSideBarAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = ActiveAreaPart.getSideBar();
	}

	public static void update(MouseEvent event)
	{
		double sceneX = event.getSceneX() - 45;

		if (HideSideBarAction.isHided())
		{
			// 100 밖으로 드래그 시 나타남
			if (100 <= sceneX)
			{
				HideSideBarAction.update(false);
			}
		}
		else
		{
			// 180 안에서는 사이즈 변경 X
			if (sceneX <= 180)
			{
				// 90 안으로 드래그 시 숨김
				if (sceneX <= 90)
				{
					HideSideBarAction.update(true);
				}
			}
			else
			{
				pane.setPrefWidth(sceneX);
			}
		}
	}
}