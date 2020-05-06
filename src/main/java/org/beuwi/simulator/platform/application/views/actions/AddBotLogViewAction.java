package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.managers.LogManager;
import org.beuwi.simulator.platform.ui.components.ILogView;

public class AddBotLogViewAction
{
	public static void update(String name)
	{
		LogManager.data.put(name, new ILogView(name));
	}
}