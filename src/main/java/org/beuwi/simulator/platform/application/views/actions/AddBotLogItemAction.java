package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.managers.LogManager;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;
import org.beuwi.simulator.platform.ui.components.ILogItem;
import org.beuwi.simulator.platform.ui.components.ILogView;

public class AddBotLogItemAction
{
	private static ILogView listView;

	public static void initialize()
	{
		listView = GlobalLogTab.getComponent();
	}

	// Global
	public static void update(ILogItem item)
	{
		listView.addItem(item);
		listView.scrollToLast();
	}

	public static void update(String name, ILogItem item)
	{
		ILogView logView = LogManager.getView(name);

		if (logView != null)
		{
			logView.addItem(item);
			logView.scrollToLast();
		}
	}
}