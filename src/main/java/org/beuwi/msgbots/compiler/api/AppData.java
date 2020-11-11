package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class AppData extends ScriptableObject
{
	@Override
	public String getClassName()
	{
		return "AppData";
	}
	
	@JSStaticFunction
	public static void putBoolean(String key, Boolean bool)
	{
		return ;
	}

	@JSStaticFunction
	public static void putInt(String ket, int value)
	{
		return ;
	}

	@JSStaticFunction
	public static void putString(String key, String value)
	{
		return ;
	}

	@JSStaticFunction
	public static Boolean getBoolean(String ket, Boolean value)
	{
		return null;
	}

	@JSStaticFunction
	public static int getInt(String key, int value)
	{
		return 0;
	}

	@JSStaticFunction
	public static String getString(String key, String value)
	{
		return null;
    }

	@JSStaticFunction
	public static void remove(String key)
	{
		return ;
	}

	@JSStaticFunction
	public static void clear()
	{
		return ;
	}
}