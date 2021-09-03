package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.compiler.engine.ScriptEngine;

import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.setting.ProjectSettings;
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
		if (Undefined.isUndefined(inputName)) {
			return null;
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			Project project = ProjectManager.findByName(scriptName);
			if (project != null) {
				return ProjectSettings.get(project).getBoolean("allowBridge");
			}
			else {
				return false;
			}
		}
	}
}