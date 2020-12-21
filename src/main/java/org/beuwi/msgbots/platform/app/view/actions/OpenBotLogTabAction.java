package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class OpenBotLogTabAction implements Action
{
	public static void execute(String name)
	{
		LogView logView = new LogView(name);

		FileManager.link(FileManager.getBotLog(name), () ->
		{
			logView.getItems().setAll(LogManager.load(FileManager.getBotLog(name)));
		});

		AddMainAreaTabAction.execute(new Tab("LOG : " + name, new LogView(name)));
	}

	@Override
	public String getName()
	{
		return "open.bot.log.tab.action";
	}
}
