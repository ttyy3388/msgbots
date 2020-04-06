package org.beuwi.simulator.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

@SuppressWarnings("serial")
public class DataBase  extends ScriptableObject
{
    @Override
    public String getClassName() 
	{
        return "DataBase";
    }

    @JSStaticFunction
	public static String setDataBase(String fileName, String data) 
	{
		return null;
	}

    @JSStaticFunction
	public static String getDataBase(String fileName) 
	{
		return null;
	}

    @JSStaticFunction
	public static String appendDataBase(String fileName, String data) 
	{
		return null;
	}
	
    @JSStaticFunction
	public static Boolean removeDataBase(String fileName) 
	{
		return null;
	}
}
