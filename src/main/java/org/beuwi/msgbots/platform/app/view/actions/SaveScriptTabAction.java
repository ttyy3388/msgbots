package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.utils.FileUtils;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.CodeArea;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;

public class SaveScriptTabAction implements Action
{
	private static TabPane pane;

	@Override
	public void init()
	{
		pane = MainAreaPart.getComponent();
	}

	public static void execute()
	{
		execute(pane.getSelectedTab());
	}

	public static void execute(String name)
	{
		execute(pane.getTab(name));
	}

	public static void execute(Tab tab)
	{
		if (tab != null)
		{
			FileUtils.save(FileManager.getBotScript(tab.getTitle()), ((CodeArea) tab.getContent()).getText());
		}
	}

	@Override
	public String getName()
	{
		return "save.script.tab.action";
	}
}
