package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.gui.control.Log.Type;
import org.mozilla.javascript.ScriptableObject;
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
		LogManager.append(data, Type.DEBUG);
	}

	@JSStaticFunction
	public void error(String data, Boolean showToast)
	{
		LogManager.append(data, Type.ERROR);
	}

	@JSStaticFunction
	public void info(String data, Boolean showToast)
	{
		LogManager.append(data, Type.EVENT);
	}

	@JSStaticFunction
	public void clear()
	{
		LogManager.clear();
	}
}