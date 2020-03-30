package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.compiler.engine.ScriptManager;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.actions.SendChatMessageAction;
import org.beuwi.simulator.settings.Settings;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

import java.io.File;
import java.util.ArrayList;

public class Api extends ScriptableObject
{
	/* ----------------------------------------- */

	public static boolean isUndefined(String name)
	{
		return name.equals("undefined");
	}

	public static String getScriptName(String name)
	{
		return (name.endsWith(".js")) ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	/* ----------------------------------------- */

	@Override
	public String getClassName()
	{
		return "Api";
	}

	@JSStaticFunction
	public static Object getContext()
	{
		return null;
	}

	@JSStaticFunction
	public static Boolean on(String name) 
	{
		name = getScriptName(name);

		if (isUndefined(name))
		{
			for (String script : FileManager.getBotNames())
			{
				BotManager.setBotPower(script, true);
			}
		} 
		else 
		{
			try
			{
				BotManager.setBotPower(name, true);
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}
	
	@JSStaticFunction
	public static Boolean off(String name) 
	{
		name = getScriptName(name);

		if (isUndefined(name))
		{
			for (String script : FileManager.getBotNames())
			{
				BotManager.setBotPower(script, false);
			}
		}
		else
		{
			try
			{
				BotManager.setBotPower(name, false);
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}
	
	@JSStaticFunction
	public static Boolean reload(String name, Boolean stopOnError)
	{
		name = getScriptName(name);

		if (!isUndefined(name))
		{
			if (!FileManager.getBotScript(name).exists())
			{
				return false;
			}

			return ScriptManager.setInitialize(name, false, !stopOnError);
		}
		else 
		{
			ScriptManager.allInitialize(false);

			return true;
		}
	}
	
	@JSStaticFunction
	public static Boolean compile(String name, Boolean stopOnError) throws Exception
	{
		return reload(name, stopOnError);
	}

	@JSStaticFunction
	public static int prepare(String name)
	{
		return 0;
	}

	@JSStaticFunction
	public static Boolean unload(String name)
	{
		name = getScriptName(name);

		if (isUndefined(name))
		{
			return false;
		}
		if (!ScriptManager.container.containsKey(name))
		{
			return false;
		}

		ScriptManager.container.remove(name);

		return true;
	}
	
	@JSStaticFunction
	public static Boolean isOn(String name)
	{
		return Settings.getScriptSetting(name).getBoolean("power");
	}

	@JSStaticFunction
	public static Boolean isCompiled(String name)
	{
		return ScriptManager.container.get(getScriptName(name)) != null;
	}

	@JSStaticFunction
	public static Boolean isCompiling(String name)
	{
		name = getScriptName(name);

		if (isUndefined(name))
		{
			for (String script : FileManager.getBotNames())
			{
				if (ScriptManager.compiling.get(script) != null)
				{
					return true;
				}
			}

			return false;
		}

		if (ScriptManager.compiling.get(name) == null)
		{
			return false;
		}

		return ScriptManager.compiling.get(name);
	}

	@JSStaticFunction
	public static Scriptable getScriptNames()
	{
		File[] files = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

		ArrayList<String> list = new ArrayList<>();

		for (File file : files)
		{
			list.add(file.getName());
		}

		return Context.enter().newArray(ScriptManager.execScope, list.toArray());
	}

	@JSStaticFunction
	public static Boolean replyRoom(String room, String message, Boolean hideToast)
	{
		SendChatMessageAction.update(message, false);

		return true;
	}

	@JSStaticFunction
	public static Boolean canReply(String room)
	{
		return true;
	}

	@JSStaticFunction
	public static void showToast(String title, String data) 
	{
		return ;
	}

	@JSStaticFunction
	public static String papagoTranslate(String sourceLanguage, String targetLanguage, String data, Boolean errorToString)
	{
		return null;
	}

	@JSStaticFunction
	public static Boolean makeNoti(String title, String data, int id)
	{
		return true;
	}

	@JSStaticFunction
	public static void gc()
	{
		System.gc();
	}

	@JSStaticFunction
	public static void UIThread(Function function, Function onComplete)
	{
		return ;
	}
}