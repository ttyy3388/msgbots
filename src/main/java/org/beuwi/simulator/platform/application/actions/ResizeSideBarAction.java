package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.parts.SideBarPart;

public class ResizeSideBarAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = SideBarPart.getRoot();
	}

	public static void update(MouseEvent event)
	{
		// 40 : Activity Bar Width
		double sceneX = event.getSceneX() - 40;

		// 이 부분에서 드래그 시 깜빡이는 문제점 방지
		if (!(sceneX >= -5 && sceneX <= 5))
		{
			/* if (WindowStage.getPrimartStage() - editorArea.getMinWidth())
			{
				return ;
			} */

			if (HideSideBarAction.isHided())
			{
				if (125 <= sceneX)
				{
					HideSideBarAction.update(false);
				}
			}
			else
			{
				// 220 안에서는 사이즈 변경 X
				if (sceneX <= 200)
				{
					// 150 안으로 드래그 시 숨김
					if (sceneX <= 125)
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
}