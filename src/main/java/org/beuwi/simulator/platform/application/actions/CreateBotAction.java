package org.beuwi.simulator.platform.application.actions;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.dialogs.ExistsBotDialog;

import java.io.File;

public class CreateBotAction
{
	public static void update(String name, String content, boolean isImport, boolean isUnified, boolean isOffError)
	{
		File project = FileManager.getBotFolder(name);

		if (project.exists())
		{
			new ExistsBotDialog(name).display();
		}
		else
		{
			project.mkdir();

			if (!isImport)
			{
				if (!isUnified)
				{
					content = FileManager.read(FileManager.getDataFile("script_default.js"));
				}
				else
				{
					content = FileManager.read(FileManager.getDataFile("script_unified.js"));
				}
			}

			FileManager.save(FileManager.getBotScript(name), content);

			FileManager.save(FileManager.getBotLog(name), "[]");

			FileManager.save
			(
				FileManager.getBotSetting(name),
				"{\"optimization\":1," +
			    "\"useUnifiedParams\":" + isUnified + "," +
				"\"offOnRuntimeError\":" + isOffError + "," +
				"\"power\":false}"
			);
		}
	}
}