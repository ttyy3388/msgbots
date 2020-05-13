package org.beuwi.simulator.compiler.engine;

import org.beuwi.simulator.compiler.api.ImageDB;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.settings.Settings;

public class ScriptManager extends ScriptEngine
{
	public static void run(String message)
	{
		Settings.Public data = Settings.getPublicSetting("debug");

		String  room 		= data.getString("room");
		String  sender 		= data.getString("sender");
		boolean isGroupChat = data.getBoolean("isGroupChat");
		String  packageName = data.getString("package");

		run(room, message, sender, isGroupChat, new ImageDB(), packageName);
	}

	public static boolean setInitialize(String name, boolean isManual, boolean ignoreError)
	{
		return initialize(name, isManual, ignoreError);
	}

	public static void allInitialize(boolean isManual)
	{
		for (String name : FileManager.getBotNames())
		{
			initialize(name, isManual, true);
		}
	}

	public static void preInitialize()
	{
		if (!Settings.getPublicSetting("program").getBoolean("autoCompile"))
		{
			return ;
		}

		for (String name : FileManager.getBotNames())
		{
			if (!BotManager.getPower(name))
            {
                continue ;
            }

			initialize(name, true, false);
		}
	}
}
