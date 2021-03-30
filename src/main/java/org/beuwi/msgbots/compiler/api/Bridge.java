package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.compiler.engine.ScriptEngine;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class Bridge extends ScriptableObject {
    @Override
    public String getClassName() {
        return "Bridge";
    }

    @JSStaticFunction
	public static ScriptableObject getScopeOf(String inputName) {
    	if (Undefined.isUndefined(inputName)) {
    		return null;
		}
    	else {
    		String scriptName = Utils.toScriptName(inputName);
    		if (!isAllowed(scriptName)) {
    			return null;
			}
    		if (ScriptEngine.container.get(scriptName) != null) {
    			try {
    				return ScriptEngine.container.get(scriptName).getScope();
				}
    			catch (Throwable e) {
					Context.reportError(e.toString());
					return null;
				}
			}
		}
		return null;
	}

	@JSStaticFunction
	public static Boolean isAllowed(String inputName) {
    	// return ScriptSettings.get(Utils.toScriptName(inputName)).getBoolean("allow_bridge");
		return true;
	}
}