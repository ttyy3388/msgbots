package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.compiler.engine.ScriptEngine;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class Bridge extends ScriptableObject
{
	public Bridge(ScriptableObject object)
	{
		super(object, object.getPrototype());
	}

    @Override
    public String getClassName() 
    {
        return "Bridge";
    }
    
    @JSFunction
	public ScriptableObject getScopeOf(String name) 
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

	@JSFunction
	public Boolean isAllowed(String name)
	{
		return false;
	}
}