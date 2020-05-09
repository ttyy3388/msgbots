package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.managers.LogManager;
import org.beuwi.simulator.platform.ui.components.ILogType;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

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

	@JSStaticFunction
	public void d(String data, Boolean showToast)
	{
		debug(data, showToast);
	}

	@JSStaticFunction
	public void e(String data, Boolean showToast)
	{
		error(data, showToast);
	}

	@JSStaticFunction
	public void i(String data, Boolean showToast)
	{
		info(data, showToast);
	}

	@JSStaticFunction
	public void debug(String data, Boolean showToast)
	{
		LogManager.append(name, data, ILogType.DEBUG);
	}

	@JSStaticFunction
	public void error(String data, Boolean showToast)
	{
		LogManager.append(name, data, ILogType.ERROR);
	}

	@JSStaticFunction
	public void info(String data, Boolean showToast)
	{
		LogManager.append(name, data, ILogType.EVENT);
	}

	@JSStaticFunction
	public void clear()
	{
		LogManager.clear(name);
	}
}