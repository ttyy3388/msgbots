package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class Bridge extends ScriptableObject {
    @Override
    public String getClassName() {
        return "Bridge";
    }
    
    @JSStaticFunction
	public static ScriptableObject getScopeOf(String name) {
		return null;
	}

	@JSStaticFunction
	public static Boolean isAllowed(String name) {
		return false;
	}
}