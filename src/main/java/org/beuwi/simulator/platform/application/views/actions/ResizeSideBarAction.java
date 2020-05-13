// Copyright (C) Beuwi s.r.o Use of this source code is governed by the GLP-3.0 license that can be found in the LICENSE file.
package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class ResizeSideBarAction
{
	private static StackPane pane;

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
			// 90 밖으로 드래그 시 나타남
			if (90 <= size)
			{
				HideSideBarAction.update(false);
			}
		}
		else
		{
			// 180 안에서는 사이즈 변경 X
			if (size <= 180)
			{
				// 80 안으로 드래그 시 숨김
				if (size <= 80)
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