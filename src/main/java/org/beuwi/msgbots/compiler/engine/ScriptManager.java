package org.beuwi.msgbots.compiler.engine;

import org.beuwi.msgbots.compiler.api.ImageDB;
import org.beuwi.msgbots.manager.BotManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.utils.Settings;
import org.beuwi.msgbots.platform.app.utils.Settings.Public;

public class ScriptManager extends ScriptEngine
{
	public static void run(String message)
	{
		Public data = Settings.getPublicSetting("debug");

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
			if (BotManager.getPower(name))
            {
                continue ;
            }

			initialize(name, true, false);
		}
	}
}
