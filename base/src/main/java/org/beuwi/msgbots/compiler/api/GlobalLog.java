package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.base.Logger;
import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.shared.SharedValues;

import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

import java.io.File;

public class GlobalLog extends ScriptableObject {
	@Override
	public String getClassName() {
		return "GlobalLog";
	}

	private final Session session = Session.GLOBAL;
	private final Logger logger = session.getLogger();

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
		logger.debug(data);
	}

	@JSFunction
	public void error(String data, Boolean showToast) {
		logger.error(data);
	}

	@JSFunction
	public void info(String data, Boolean showToast) {
		logger.info(data);
	}

	@JSFunction
	public void clear() {
		Dfile dfile = SharedValues.getDfile("dfile.globalLog");
		// 파일이 생성 돼 있다면
		if (dfile.isCreated()) {
			dfile.setData("[]");
		}
	}
}