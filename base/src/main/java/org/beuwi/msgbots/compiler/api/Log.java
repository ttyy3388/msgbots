package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Logger;
import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.base.type.LogType;

import org.beuwi.msgbots.manager.FileManager;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

public class Log extends ScriptableObject {
	@Override
	public String getClassName() {
		return "Log";
	}

	@Override
	public String toString() {
		return getClassName();
	}

	private final Project project;
	private final Session session;
	private final Logger logger;

	public Log(ScriptableObject object, Project project) {
		super(object, object.getPrototype());
		this.project = project;
		this.session = project.getSession();
		this.logger = session.getLogger();
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
		FileManager.write(project.getFile("log.json"), "[]");
	}
}