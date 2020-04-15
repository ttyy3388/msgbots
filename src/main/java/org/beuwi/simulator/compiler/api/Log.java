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

    public Log(String name)
    {

    }

    @JSStaticFunction
    public void d(String data, Boolean showToast)
    {
        debug(data, showToast);
    }
    
    @JSStaticFunction
    public void e(String data, Boolean showToast)
    {
        error(data, showToast);
    }
    
    @JSStaticFunction
    public void i(String data, Boolean showToast)
    {
        info(data, showToast);
    }
    
    @JSStaticFunction
	public void debug(String data, Boolean showToast)
    {
        return ;
	}
    
    @JSStaticFunction
	public void error(String data, Boolean showToast)
    {
        return ;
	}
    
    @JSStaticFunction
	public void info(String data, Boolean showToast)
    {
        return ;
	}
    
    @JSStaticFunction
	public void clear()
    {
        return ;
	}
}