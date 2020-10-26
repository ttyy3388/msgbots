package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;

public class AddEditorAreaTabAction implements Action
{
	private static TabPane pane;

	@Override
	public void init()
	{
		pane = EditorAreaPart.getComponent();
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