package org.beuwi.simulator.managers;

import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.control.CheckBox;
import org.beuwi.simulator.platform.application.actions.AddExplorerItemAction;
import org.beuwi.simulator.platform.application.actions.ClearExplorerItemsAction;
import org.beuwi.simulator.platform.application.views.dialogs.ExistsBotDialog;

import java.io.File;
import java.util.HashMap;

public class BotManager
{
	public static HashMap<String, Object> data = new HashMap<>();

	public static void setBotPower(String name, boolean power)
	{
		((JFXToggleButton) data.get("@switch::" + name)).setSelected(power);
	}

	public static void setBotCompiled(String name, boolean compiled)
	{
		((CheckBox) data.get("@check::" + name)).setSelected(compiled);
	}

	public static void refresh()
	{
		// data.clear();

		ClearExplorerItemsAction.update();

		File[] folders = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

		for (File folder : folders)
		{
			AddExplorerItemAction.update(folder.getName());
		}
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