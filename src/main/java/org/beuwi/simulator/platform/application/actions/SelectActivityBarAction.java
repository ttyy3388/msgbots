package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.views.parts.ActivityBarPart;

public class SelectActivityBarAction
{
	private static VBox voxActivityBar;

	public static void initialize()
	{
		voxActivityBar = (VBox) ActivityBarPart.getNameSpace().get("voxActivityBar");
	}

	// Selected Toggle Button Index
	public static void update(int index)
	{
		ToggleButton target = (ToggleButton) voxActivityBar.getChildren().get(index);
		ToggleButton other  = (ToggleButton) voxActivityBar.getChildren().get(index == 0 ? 1 : 0);

		// Resize 시 Hide / Show 는 나중에 구현

		/*
		1. 다른 탭을 선택 시 Change
		2. 이미 선택돼 있는 탭을 다시 선택 시 Hide
		3. 숨김 상태에서 탭 선택 시 Show
		*/

		// 이미 선택 돼 있는 탭을 다시 선택 시 Hide
		/* if (!target.isSelected())
		{
			HideSideBarAction.update(true);
		}
		else
		{ */
			// 다른 탭 선택할 경우 Change
			if (other.isSelected())
			{
				other.setSelected(false);
				target.setSelected(true);
			/* }
			// 숨김 상태에서 탭 선택 시 Show
			else
			{
				HideSideBarAction.update(false);
			} */

			ChangeSideBarAction.update(index);
		}
	}
}