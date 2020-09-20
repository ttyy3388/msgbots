package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;

public class GlobalLog extends ScriptableObject
{
	@Override
	public String getClassName()
	{
		return "GlobalLog";
	}

	public GlobalLog(ScriptableObject object)
	{
		super(object, object.getPrototype());
	}
}
