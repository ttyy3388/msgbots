package org.beuwi.simulator.managers;

import org.beuwi.simulator.platform.application.views.dialogs.ExistsBotDialog;

import java.io.File;

public class BotManager
{
	public static void refresh()
	{
		/*

		// ExplorerPart.clear();

		File[] folders = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

		for (File folder : folders)
		{
			AddExplorerItemAction.update(folder.getName());
		}

		*/
	}

	public static void Import(File file, boolean isUnified, boolean isOffError)
	{
		create(file.getName(), FileManager.read(file), true, isUnified, isOffError);
	}

	public static void create(String name, String content, boolean isImport, boolean isUnified, boolean isOffError)
	{
		File project = FileManager.getBotFolder(name);

		if (project.exists())
		{
			new ExistsBotDialog(name).display();
		}
		else
		{
		    project.mkdir();

		    content = isImport ? content : FileManager.read(BotManager.class.getResource("/datas/" + ("Script" + (isUnified ? "Default" : "Unified") + ".js")).toExternalForm());

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

	public static void rename(String name, String newName)
	{
	    File before = FileManager.getBotFolder(name);
	    File after  = FileManager.getBotFolder(newName);

	    if (after.exists())
        {
            new ExistsBotDialog(newName).display();
        }
	    else
        {
            before.renameTo(after);
        }
	}

	public static void delete(String name)
	{
		FileManager.remove(FileManager.getBotFolder(name));
	}
}