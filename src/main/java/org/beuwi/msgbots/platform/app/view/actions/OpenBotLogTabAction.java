package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class OpenBotLogTabAction implements Action
{
	public static void execute(String name)
	{
	   // AddEditorAreaTabAction.execute(new Tab(name, LogManager.getView(name)));
	}

	@Override
	public String getName()
	{
		return "open.bot.log.tab.action";
	}
}
