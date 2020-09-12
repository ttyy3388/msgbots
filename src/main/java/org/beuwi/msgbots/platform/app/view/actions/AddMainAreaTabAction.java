package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;

public class AddMainAreaTabAction implements Action
{
	private static TabPane pane;

	@Override
	public void init()
	{
		pane = MainAreaPart.getComponent();
	}

	public static void execute(Tab tab)
	{
		pane.addTab(tab);
	}

	@Override
    public String getName()
    {
        return "add.main.area.tab.action";
    }
}