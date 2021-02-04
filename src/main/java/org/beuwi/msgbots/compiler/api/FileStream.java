package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class FileStream extends ScriptableObject
{
    @Override
    public String getClassName() 
    {
        return "FileStream";
    }

	@JSStaticFunction
	public static String read(String path)
	{
		return null;
	}

	@JSStaticFunction
	public static String write(String path, String data)
	{
		return null;
	}

	@JSStaticFunction
	public static String append(String path, String data)
	{
		return null;
	}
	
	@JSStaticFunction
    public static Boolean remove(String path)
	{
    	return null;
    }

    @JSStaticFunction
	public static Boolean create(String path)
	{
		return null;
	}
}