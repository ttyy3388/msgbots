package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.TabPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class SelectActivityTabAction
{
	private static TabPane pane;

	public static void initialize()
	{
		pane = ActiveAreaPart.getComponent();
	}

	/*

		Hide
		1. Show 상태에서 이미 선택된 탭을 다시 선택할 경우
		2. Show 상태에서 일정 이하 너비로 줄일 경우

		Show
		1. Hide 상태에서 탭을 선택할 경우
		2. Hide 상태에서 일정 이상 너비로 늘릴 경우

	*/

	public static void update(int index)
	{
		System.out.println(index);

		// Show
		if (!HideSideBarAction.isHided())
		{
			// 이미 선택된 탭을 다시 선택할 경우
			if (index == pane.getSelectionModel().getSelectedIndex())
			{
				HideSideBarAction.update(true);
			}
		}
		// Hide
		else
		{
			// 탭을 선택할 경우
			HideSideBarAction.update(false);
		}
	}
}