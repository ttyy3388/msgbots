package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ArrayList;
import java.util.List;

public class CloseOtherTabsAction
{
	public static void update(Tab target)
	{
		List<Tab> list = new ArrayList<>();

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