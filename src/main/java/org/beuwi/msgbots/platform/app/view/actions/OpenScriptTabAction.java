package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.CodeArea;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class OpenScriptTabAction implements Action
{
	@Override
	public void init()
	{

	}

	public static void execute(String name)
	{
		AddMainAreaTabAction.execute
		(
			new Tab(name, new CodeArea(/* FileManager.read(FileManager.getBotScript(name) */))
		);
	}

	@Override
	public String getName()
	{
		return "open.script.tab.action";
	}
}
