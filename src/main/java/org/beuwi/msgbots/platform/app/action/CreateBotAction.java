package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.utils.FileUtils;

import java.io.File;

public class CreateBotAction implements Action
{
	@Override
	public void init()
	{

	}

	public static void execute(String name, boolean isUnified, boolean isOffError)
	{
		execute(name, null, false, isUnified, isOffError);
	}

	public static void execute(String name, String content, boolean isImport, boolean isUnified, boolean isOffError)
	{
		File project = FileManager.getBotFolder(name);

		if (project.exists())
		{
			System.out.println("exists project");
		}
		else
		{
			project.mkdir();

			if (!isImport)
			{
				if (!isUnified)
				{
					content = FileUtils.read(FileManager.getDataFile("script_default.js"));
				}
				else
				{
					content = FileUtils.read(FileManager.getDataFile("script_unified.js"));
				}
			}

			FileUtils.save(FileManager.getBotScript(name), content);

			FileUtils.save(FileManager.getBotLog(name), "[]");

			FileUtils.save
			(
				FileManager.getBotSetting(name),
				"{\"optimization\":1," +
				"\"useUnifiedParams\":" + isUnified + "," +
				"\"offOnRuntimeError\":" + isOffError + "," +
				"\"power\":false}"
			);
		}
	}

	@Override
	public String getName()
	{
		return "create.bot.action";
	}
}
