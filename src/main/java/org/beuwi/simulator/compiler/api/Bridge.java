package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.compiler.engine.ScriptEngine;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

@SuppressWarnings("serial")
public class Bridge extends ScriptableObject
{
    @Override
    public String getClassName() 
    {
        return "Bridge";
    }
    
    @JSStaticFunction
	public static ScriptableObject getScopeOf(String name) 
 	{
        if (ScriptEngine.container.get(name) == null)
        {
        	return null;
        }
        else
        {
        	try
		    {
		    	return ScriptEngine.container.get(name).getScope();
		    }
		    catch(Throwable e)
		    {
				Context.reportError(e.toString());
		    }

			return null;
        }
 	}
}