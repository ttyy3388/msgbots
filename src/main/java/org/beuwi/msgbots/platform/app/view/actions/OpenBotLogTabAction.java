package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.openapi.FileWatcher;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.Tab;

import java.io.File;

public class OpenBotLogTabAction implements Action
{
	public static void execute(String name)
	{
		LogView logView = new LogView(name);

		FileManager.link(FileManager.getBotLog(name), () ->
		{
			logView.setItems(LogManager.load(FileManager.getBotLog(name)));
		});

		AddEditorAreaTabAction.execute(new Tab("log:" + name, new LogView(name)));
	}

	@Override
	public String getName()
	{
		return "open.bot.log.tab.action";
	}
}
