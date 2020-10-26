package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.editor.CodeArea;

public class OpenScriptTabAction implements Action
{
	@Override
	public void init()
	{

	}

	public static void execute(String name)
	{
		AddEditorAreaTabAction.execute(new Tab(name, new CodeArea("TEST")));
	}

	@Override
	public String getName()
	{
		return "open.script.tab.action";
	}
}
