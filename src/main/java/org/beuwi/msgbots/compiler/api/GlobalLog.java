package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSStaticFunction;

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

	@JSFunction
	public void d(String data, Boolean showToast)
	{
		debug(data, showToast);
	}

	@JSFunction
	public void e(String data, Boolean showToast)
	{
		error(data, showToast);
	}

	@JSFunction
	public void i(String data, Boolean showToast)
	{
		info(data, showToast);
	}

	@JSFunction
	public void debug(String data, Boolean showToast)
	{
		return ;
	}

	@JSFunction
	public void error(String data, Boolean showToast)
	{
		return ;
	}

	@JSFunction
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