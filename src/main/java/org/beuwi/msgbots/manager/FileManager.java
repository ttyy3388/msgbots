package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;

public class FileManager
{
	public static String getBaseName(String name)
	{
		return name.contains(".") ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	public static String getExtension(String name)
	{
		return name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
	}

    public static File getDataFile(String name)
    {
        return new File(SharedValues.DATA_FOLDER_FILE + File.separator + name);
    }

    public static File[] getBotList()
	{
		return SharedValues.BOTS_FOLDER_FILE.listFiles();
	}

	public static File[] getBotFiles()
	{
		return SharedValues.BOTS_FOLDER_FILE.listFiles();
	}

	public static String[] getBotNames()
	{
		File[] files = SharedValues.BOTS_FOLDER_FILE.listFiles(File::isDirectory);
		String[] names = new String[files.length];

		for (int i = 0 ; i < files.length ; i ++)
		{
			names[i] = files[i].getName();
		}

		return names;
	}

	public static File getBotFolder(String name)
	{
		return new File(SharedValues.BOTS_FOLDER_FILE + File.separator  + getBaseName(name));
	}

	public static File getBotScript(String name)
	{
		return new File(getBotFolder(name).getPath() + File.separator + "index.js");
	}

	public static File getBotSetting(String name)
	{
		return new File(getBotFolder(name).getPath() + File.separator + "bot.json");
	}

	public static File getBotLog(String name)
	{
		return new File(getBotFolder(name).getPath() + File.separator + "log.json");
	}

	/* ----------------------------------------------------------------------------------- */


}