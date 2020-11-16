package org.beuwi.msgbots.compiler.engine;

import org.beuwi.msgbots.compiler.api.ImageDB;
import org.beuwi.msgbots.manager.BotManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.setting.GlobalSettings;

public class ScriptManager extends ScriptEngine
{
	public static void run(String message)
	{
		ScriptEngine.run("room", message, "sender", false, new ImageDB(),"packageName");
	}

	public static void preInit()
	{
		if (!GlobalSettings.getBoolean("program.start_auto_compile"))
		{
			return ;
		}

		for (String name : FileManager.getBotNames())
		{
			if (!BotManager.getPower(name))
			{
				continue ;
			}

			ScriptEngine.initialize(name, true, false);
		}
	}

	public static void initAll(boolean isManual)
	{
		for (String name : FileManager.getBotNames())
		{
			ScriptEngine.initialize(name, isManual, true);
		}
	}

	public static boolean initBot(String name, boolean isManual, boolean ignoreError)
	{
		return ScriptEngine.initialize(name, isManual, ignoreError);
	}
}