package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.compiler.engine.ScriptEngine;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

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
		if (ScriptEngine.container.get(name) != null)
		{
			try
			{
				return ScriptEngine.container.get(name).getScope();
			}
			catch (Throwable e)
			{
				Context.reportError(e.toString());
			}

		}

		return null;
	}

	@JSStaticFunction
	public static Boolean isAllowed(String name)
	{
		return false;
	}
}