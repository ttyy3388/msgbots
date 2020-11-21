package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;

public class AddMainAreaTabAction implements Action
{
	private static TabView control;

	@Override
	public void init()
	{
		control = MainAreaPart.getComponent();
	}

	public static void execute(Tab tab)
	{
		control.addTab(tab);
	}

	@Override
    public String getName()
    {
        return "add.main.area.tab.action";
    }
}