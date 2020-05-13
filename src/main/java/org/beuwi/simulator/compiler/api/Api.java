package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.compiler.engine.ScriptEngine;
import org.beuwi.simulator.compiler.engine.ScriptManager;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.actions.AddChatMessageAction;
import org.beuwi.simulator.platform.application.views.actions.ShowNotificationAction;
import org.beuwi.simulator.platform.application.views.actions.ShowToastMessageAction;
import org.beuwi.simulator.settings.Settings;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSFunction;

import java.io.File;
import java.util.ArrayList;

public class Api extends ScriptableObject
{
	final String name;

	public Api(ScriptableObject object, String name)
	{
		super(object, object.getPrototype());

		this.name = name;
	}

	private String getScriptName(String name)
	{
		return name.endsWith(".js") ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	@Override
	public String getClassName()
	{
		return "Api";
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
	
	@JSFunction
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

	@JSFunction
	public int prepare(String name)
	{
		return 0;
	}

	@JSFunction
	public Boolean unload(String name)
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
	
	@JSFunction
	public Boolean isOn(String name)
	{
		return Settings.getScriptSetting(name).getBoolean("power");
	}

	@JSFunction
	public Boolean isCompiled(String name)
	{
		return ScriptEngine.container.get(getScriptName(name)) != null;
	}

	@JSFunction
	public Boolean isCompiling(String name)
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

	@JSFunction
	public Scriptable getScriptNames()
	{
		File[] files = FileManager.BOTS_FOLDER.listFiles(File::isDirectory);

		ArrayList<String> list = new ArrayList<>();

		for (File file : files)
		{
			list.add(file.getName());
		}

		return Context.enter().newArray(ScriptEngine.execScope, list.toArray());
	}

	@JSFunction
	public Boolean replyRoom(String room, String message, Boolean hideToast)
	{
		AddChatMessageAction.update(message, false);

		return true;
	}

	@JSFunction
	public Boolean canReply(String room)
	{
		return true;
	}

	@JSFunction
	public void showToast(String content, int length)
	{
		ShowToastMessageAction.update(content);
	}

	@JSFunction
	public String papagoTranslate(String sourceLanguage, String targetLanguage, String data, Boolean errorToString)
	{
		return null;
	}

	@JSFunction
	public Boolean makeNoti(String title, String content, int id)
	{
		ShowNotificationAction.update(title, content);

		return true;
	}

	@JSFunction
	public void gc()
	{
		System.gc();
	}

	@JSFunction
	public void UIThread(Function function, Function onComplete)
	{
		return ;
	}
}