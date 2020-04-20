package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.ui.log.ILogView;

public class OpenBotLogTabAction
{
	public static void update(String name)
	{
		AddEditorTabAction.update("@log::" + name, "Log : " + name, new ILogView(name));
	}
}