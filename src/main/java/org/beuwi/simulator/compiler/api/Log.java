package org.beuwi.simulator.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class Log extends ScriptableObject
{
    @Override
    public String getClassName() 
    {
        return "Log";
    }

    @JSStaticFunction
    public static void d(String data, Boolean showToast)
    {
        debug(data, showToast);
    }
    
    @JSStaticFunction
    public static void e(String data, Boolean showToast)
    {
        error(data, showToast);
    }
    
    @JSStaticFunction
    public static void i(String data, Boolean showToast)
    {
        info(data, showToast);
    }
    
    @JSStaticFunction
	public static void debug(String data, Boolean showToast)
    {
        return ;
	}
    
    @JSStaticFunction
	public static void error(String data, Boolean showToast)
    {
        return ;
	}
    
    @JSStaticFunction
	public static void info(String data, Boolean showToast)
    {
        return ;
	}
    
    @JSStaticFunction
	public static void clear()
    {
        return ;
	}
}