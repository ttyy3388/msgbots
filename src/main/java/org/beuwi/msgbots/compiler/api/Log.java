package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class Log extends ScriptableObject
{
	@Override
	public String getClassName()
	{
		return "Log";
	}

	@Override
	public String toString()
	{
		return getClassName();
	}

	final String name;

	public Log(ScriptableObject object, String name)
	{
		super(object, object.getPrototype());

		this.name = name;
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

	@JSFunction
	public void clear()
	{
		return ;
	}
}