package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;

public class FileManager
{
	public static final File MAIN_FOLDER = new File(SharedValues.MAIN_FOLDER_PATH);
	public static final File DATA_FOLDER = new File(SharedValues.DATA_FOLDER_PATH);
	public static final File BOTS_FOLDER = new File(SharedValues.BOTS_FOLDER_PATH);

	public static String getBaseName(String name)
	{
		return (name.contains(".")) ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	public static String getExtension(String name)
	{
		return (name.contains(".")) ? name.substring(name.lastIndexOf(".") + 1) : name;
	}

    public static File getDataFile(String name)
    {
        return new File(DATA_FOLDER + File.separator + name);
    }

    public static File[] getBotList()
	{
		return BOTS_FOLDER.listFiles();
	}

	public static String[] getBotNames()
	{
		File[] files = BOTS_FOLDER.listFiles(File::isDirectory);
		String[] names = new String[files.length];

		for (int i = 0 ; i < files.length ; i ++)
		{
			names[i] = files[i].getName();
		}

		return names;
	}

	/* public static File getBotsFolder()
	{
		return BOTS_FOLDER;
	} */

	public static File getBotFolder(String name)
	{
		return new File(BOTS_FOLDER + File.separator  + getBaseName(name));
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

}