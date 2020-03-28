package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.compiler.engine.ScriptManager;
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
        if (ScriptManager.container.get(name) == null)
        {
        	return null;
        }
        else
        {
        	try
		    {
		    	return ScriptManager.container.get(name).getScope();
		    }
		    catch(Throwable e)
		    {
				Context.reportError(e.toString());
		    }

			return null;
        }
 	}
}