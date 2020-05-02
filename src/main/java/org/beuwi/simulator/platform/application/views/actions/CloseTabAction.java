package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.ui.components.ITab;

import java.util.Collection;

public class CloseTabAction
{
	public static void update(ITab tab)
	{
		tab.getITabPane().closeTab(tab);
	}

	public static void update(Collection tabs)
	{
		tabs.removeAll(tabs);
	}
}