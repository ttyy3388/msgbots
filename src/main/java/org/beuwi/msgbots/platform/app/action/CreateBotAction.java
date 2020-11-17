package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.util.SharedValues;

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
			System.out.println("already exists");
		}
		else
		{
			project.mkdir();

			if (!isImport)
			{
				if (!isUnified)
				{
					content = FileManager.read(SharedValues.SCRIPT_DEFAULT_FILE);
				}
				else
				{
					content = FileManager.read(SharedValues.SCRIPT_UNIFIED_FILE);
				}
			}

			FileManager.save(FileManager.getBotScript(name), content);

			FileManager.save(FileManager.getBotLog(name), "[]");

			FileManager.save
			(
				FileManager.getBotSetting(name),
				"{\"optimization\":1," +
				"\"use_unified_params\":" + isUnified + "," +
				"\"off_on_runtime_error\":" + isOffError + "," +
				"\"power\":false," +
				"\"ignore_api_off\":false}"
			);
		}
	}

	@Override
	public String getName()
	{
		return "create.bot.action";
	}
}
