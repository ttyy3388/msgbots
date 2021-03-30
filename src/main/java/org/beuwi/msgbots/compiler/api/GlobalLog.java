package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.gui.enums.LogType;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class GlobalLog extends ScriptableObject {
	@Override
	public String getClassName() {
		return "GlobalLog";
	}

	public GlobalLog(ScriptableObject object) {
		super(object, object.getPrototype());
	}

	@JSFunction
	public void d(String data, Boolean showToast) {
		debug(data, showToast);
	}

	@JSFunction
	public void e(String data, Boolean showToast) {
		error(data, showToast);
	}

	@JSFunction
	public void i(String data, Boolean showToast) {
		info(data, showToast);
	}

	@JSFunction
	public void debug(String data, Boolean showToast) {
		LogManager.appendGlobal(LogType.DEBUG, data);
	}

	@JSFunction
	public void error(String data, Boolean showToast) {
		LogManager.appendGlobal(LogType.ERROR, data);
	}

	@JSFunction
	public void info(String data, Boolean showToast) {
		LogManager.appendGlobal(LogType.EVENT, data);
	}

	@JSFunction
	public void clear() {
		LogManager.clearGlobal();
	}
}