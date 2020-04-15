package org.beuwi.simulator.platform.application.actions;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorIDialog;

import java.awt.*;
import java.io.File;

public class OpenDesktopAction
{
	public static void update()
	{
		update(FileManager.BOTS_FOLDER);
	}

	public static void update(String path)
	{
		update(new File(path));
	}

	public static void update(File file)
	{
		try
		{
			Desktop.getDesktop().open(file);
		}
		catch (Exception e)
		{
			new ShowErrorIDialog(e).display();
		}
	}
}