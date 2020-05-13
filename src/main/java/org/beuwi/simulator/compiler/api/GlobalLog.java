package org.beuwi.simulator.compiler.api;

import org.beuwi.simulator.managers.LogManager;
import org.beuwi.simulator.platform.ui.components.ILogType;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class GlobalLog extends ScriptableObject
{
	public GlobalLog(ScriptableObject object)
	{
		super(object, object.getPrototype());
	}

	@Override
	public String getClassName()
	{
		return "GlobalLog";
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
		LogManager.append( data, ILogType.DEBUG);
	}

	@JSFunction
	public void error(String data, Boolean showToast)
	{
		LogManager.append(data, ILogType.ERROR);
	}

	@JSFunction
	public void info(String data, Boolean showToast)
	{
		LogManager.append(data, ILogType.EVENT);
	}

	@JSFunction
	public void clear()
	{
		LogManager.clear();
	}
}