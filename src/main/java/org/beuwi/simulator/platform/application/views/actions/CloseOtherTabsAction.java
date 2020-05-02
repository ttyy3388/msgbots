package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;

public class CloseOtherTabsAction
{
	// 현재 탭 닫기
	public static void update(Tab target)
	{
		ArrayList<Tab> list = new ArrayList<>();

		TabPane pane = target.getTabPane();

		for (Tab tab : pane.getTabs())
		{
			if (tab != target)
			{
				list.add(tab);
			}
		}

		CloseTabAction.update(list);
	}
}