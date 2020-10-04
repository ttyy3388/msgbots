package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.compiler.engine.ScriptEngine;
import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.manager.BotManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.utils.Settings;
import org.beuwi.msgbots.platform.app.view.actions.AddChatMessageAction;
import org.beuwi.msgbots.platform.app.view.actions.ShowNotificationAction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSStaticFunction;

import java.io.File;
import java.util.ArrayList;

public class Api extends ScriptableObject
{
	@Override
	public String getClassName()
	{
		return "Api";
	}

	// 추후 제거
	private static String getScriptName(String name)
	{
		return name.endsWith(".js") ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	final String name;

	public Api(ScriptableObject object, String name)
	{
		super(object, object.getPrototype());

		this.name = name;
	}

	@JSFunction
	public Object getContext()
	{
		return null;
	}

	@JSFunction
	public Boolean on(String name)
	{
		name = getScriptName(name);

		if (Undefined.isUndefined(name))
		{
			for (String script : FileManager.getBotNames())
			{
				BotManager.setPower(script, true);
			}
		} 
		else 
		{
			try
			{
				BotManager.setPower(name, true);
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}
	
	@JSStaticFunction
	public Boolean off(String name)
	{
		name = getScriptName(name);

		if (Undefined.isUndefined(name))
		{
			for (String script : FileManager.getBotNames())
			{
				BotManager.setPower(script, false);
			}
		}
		else
		{
			try
			{
				BotManager.setPower(name, false);
			}
			catch (Exception e)
			{
				return false;
			}
		}

		return true;
	}
	
	@JSFunction
	public Boolean reload(String name, Boolean stopOnError)
	{
		name = getScriptName(name);

		if (!Undefined.isUndefined(name))
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
	
	@JSFunction
	public Boolean compile(String name, Boolean stopOnError) throws Exception
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

		if (Undefined.isUndefined(name))
		{
			return false;
		}
		if (!ScriptEngine.container.containsKey(name))
		{
			return false;
		}

		ScriptEngine.container.remove(name);

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
		return ScriptEngine.container.get(getScriptName(name)) != null;
	}

	@JSStaticFunction
	public static Boolean isCompiling(String name)
	{
		name = getScriptName(name);

		if (Undefined.isUndefined(name))
		{
			for (String script : FileManager.getBotNames())
			{
				if (ScriptEngine.compiling.get(script) != null)
				{
					return true;
				}
			}

			return false;
		}

		if (ScriptEngine.compiling.get(name) == null)
		{
			return false;
		}

		return ScriptEngine.compiling.get(name);
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

		return Context.enter().newArray(ScriptEngine.execScope, list.toArray());
	}

	@JSStaticFunction
	public static Boolean replyRoom(String room, String message, Boolean hideToast)
	{
		AddChatMessageAction.execute(message, false);

		return true;
	}

	@JSStaticFunction
	public static Boolean canReply(String room)
	{
		return true;
	}

	@JSStaticFunction
	public static void showToast(String content, int length)
	{
		// AddToastMessageAction.execute(content);
	}

	@JSStaticFunction
	public static String papagoTranslate(String sourceLanguage, String targetLanguage, String data, Boolean errorToString)
	{
		return null;
	}

	@JSStaticFunction
	public static Boolean makeNoti(String title, String content, int id)
	{
		ShowNotificationAction.execute(title, content);

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