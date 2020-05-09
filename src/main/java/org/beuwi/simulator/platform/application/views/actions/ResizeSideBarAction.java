package org.beuwi.simulator.platform.application.views.actions;

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
		// 30 : Activity Bar Width
		double size = event.getSceneX() - 30;

		if (HideSideBarAction.isHided())
		{
			// 100 밖으로 드래그 시 나타남
			if (100 <= size)
			{
				HideSideBarAction.update(false);
			}
		}
		else
		{
			// 180 안에서는 사이즈 변경 X
			if (size <= 180)
			{
				// 90 안으로 드래그 시 숨김
				if (size <= 90)
				{
					HideSideBarAction.update(true);
				}
			}
			else
			{
				pane.setPrefWidth(size);
			}
		}
	}
}