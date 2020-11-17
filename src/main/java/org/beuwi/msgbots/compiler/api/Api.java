package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.setting.ScriptSettings;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSStaticFunction;

import java.util.function.Function;

public class Api extends ScriptableObject
{
	@Override
	public String getClassName()
	{
		return "Api";
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
		return true;
	}
	
	@JSStaticFunction
	public Boolean off(String name)
	{
		if (ScriptSettings.get(name).getBoolean("ignore_api_off"))
		{
			return false;
		}

		return true;
	}
	
	@JSFunction
	public Boolean reload(String name, Boolean stopOnError)
	{
		return true;
	}
	
	@JSFunction
	public Boolean compile(String name, Boolean stopOnError) throws Exception
	{
		return false;
	}

	@JSStaticFunction
	public static int prepare(String name)
	{
		return 0;
	}

	@JSStaticFunction
	public static Boolean unload(String name)
	{
		return true;
	}
	
	@JSStaticFunction
	public static Boolean isOn(String name)
	{
		return false;
	}

	@JSStaticFunction
	public static Boolean isCompiled(String name)
	{
		return false;
	}

	@JSStaticFunction
	public static Boolean isCompiling(String name)
	{
		return false;
	}

	@JSStaticFunction
	public static Scriptable getScriptNames()
	{
		return null;
	}

	@JSStaticFunction
	public static Boolean replyRoom(String room, String message, Boolean hideToast)
	{
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
		return ;
	}

	@JSStaticFunction
	public static String papagoTranslate(String sourceLanguage, String targetLanguage, String data, Boolean errorToString)
	{
		return null;
	}

	@JSStaticFunction
	public static Boolean makeNoti(String title, String content, int id)
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