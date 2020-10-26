package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.gui.control.Bot;
import org.beuwi.msgbots.platform.gui.control.BotView;

import java.io.File;

public class RefreshBotListAction implements Action
{
	private static BotView botView;

	@Override
	public void init()
	{
		botView = SideAreaPart.BotListTab.getComponent();
	}

	public static void execute()
	{
		botView.clear();

		for (File folder : FileManager.getBotList())
		{
			botView.addItem(new Bot(folder.getName()));
		}
	}

	@Override
	public String getName()
	{
		return "refresh.bot.list.action";
	}
}
