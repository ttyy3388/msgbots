package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;

public class OpenProgramTabAction implements Action
{
	@Override
	public void init()
	{

	}

	public static void execute(Tab tab)
	{
		execute(EditorAreaPart.getComponent(), tab);
	}

	public static void execute(TabView control, Tab tab)
	{
		control.addTab(tab);
	}

	@Override
	public String getName()
	{
		return "open.program.tab.action";
	}
}