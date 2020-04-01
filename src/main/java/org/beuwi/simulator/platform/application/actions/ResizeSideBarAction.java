package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class ResizeSideBarAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = ActiveAreaPart.getRoot();
	}

	public static void update(MouseEvent event)
	{
		double sceneX = event.getSceneX();

		if (HideSideBarAction.isHided())
		{
			// 140 밖으로 드래그 시 나타남
			if (140 <= sceneX)
			{
				HideSideBarAction.update(false);
			}
		}
		else
		{
			// 220 안에서는 사이즈 변경 X
			if (sceneX <= 220)
			{
				// 130 안으로 드래그 시 숨김
				if (sceneX <= 130)
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