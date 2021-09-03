package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.base.type.ToastType;
import org.beuwi.msgbots.compiler.engine.ScriptEngine;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.setting.ProjectSettings;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSFunction;

// 해당 클래스는 해당 패키지(org.beuwi.msgbots.compiler)의 내부에서만 접근해야 함
public class Api extends ScriptableObject {
	@Override
	public String getClassName() {
		return "Api";
	}

	// final Project project;

	public Api(ScriptableObject object) {
		super(object, object.getPrototype());
	}

	@JSFunction
	public Object getContext() {
		return null;
	}

	// inputName : 사용자가 입력한 이름 (test.js, test ...)
	// scriptName : 프로그램에서 사용할 이름(test)
	@JSFunction
	public Boolean on(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			ProjectManager.getList().forEach(project ->
				project.setPower(true)
			);
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			Project project = ProjectManager.findByName(scriptName);
			if (project != null) {
				project.setPower(true);
			}
			else {
				return false;
			}
		}

		return true;
	}
	
	@JSFunction
	public Boolean off(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			ProjectManager.getList().forEach(project ->
				project.setPower(false)
			);
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			Project project = ProjectManager.findByName(scriptName);
			if (project != null) {
				// 해당 설정이 켜져 있으면 무시
				if (ProjectSettings.get(project).getBoolean("ignoreApiOff")) {
					return false;
				}
				project.setPower(false);
			}
			else {
				return false;
			}
		}

		return true;
	}

	@JSFunction
	public Boolean compile(String inputName, Boolean stopOnError) throws Exception {
		return reload(inputName, stopOnError);
	}
	@JSFunction
	public Boolean reload(String inputName, Boolean stopOnError) {
		if (!Undefined.isUndefined(inputName)) {
			String scriptName = Utils.toScriptName(inputName);
			Project project = ProjectManager.findByName(scriptName);
			if (project != null) {
				return ScriptManager.initScript(project, false, !stopOnError);
			}

			return false;
		}
		else {
			ScriptManager.initAll(false);
			return true;
		}
	}

	@JSFunction
	public int prepare(String inputName, Boolean stopOnError) {
		if (Undefined.isUndefined(inputName)) {
			int compiled = 0;
			for (String botName : ProjectManager.getNames()) {
				if (isCompiled(botName) || isCompiling(botName) != null) {
					continue ;
				}
				else {
					reload(botName, stopOnError);
				}
				compiled ++;
			};
			return compiled;
		}
		else {
			String scriptName = Utils.toScriptName(inputName);

			if (isCompiled(scriptName)) {
				return 2;
			}
			if (reload(scriptName, stopOnError)) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

	@JSFunction
	public Boolean unload(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			return false;
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			if (ScriptEngine.container.get(scriptName) != null) {
				ScriptManager.container.remove(scriptName);
			}
			else {
				return false;
			}
			return true;
		}
	}
	
	@JSFunction
	public Boolean isOn(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			return false;
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			Project project = ProjectManager.findByName(scriptName);
			if (project != null) {
				return project.getPower();
			}
			else {
				return false;
			}
		}
	}

	@JSFunction
	public Boolean isCompiled(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			return false;
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			Project project = ProjectManager.findByName(scriptName);
			if (project != null) {
				return project.isCompiled();
			}
			else {
				return false;
			}
		}
	}

	@JSFunction
	public Boolean isCompiling(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			for (String botName : ProjectManager.getNames()) {
				if (ScriptManager.compiling.get(botName) != null) {
					return true;
				}
			};
			return false;
		}
		else {
			return ScriptManager.compiling.get(Utils.toScriptName(inputName)) != null;
		}
	}

	@JSFunction
	public Scriptable getScriptNames() {
		return Context.enter().newArray(
				ScriptEngine.execscope,
				ProjectManager.getNames().toArray()
		);
	}

	@JSFunction
	public Boolean replyRoom(String room, String message, Boolean hideToast) {
		Replier.reply(room, message, hideToast);
		return true;
	}

	@JSFunction
	public Boolean canReply(String room) {
		return true;
	}

	@JSFunction
	public void showToast(String content, int length) {
		Session session = Session.GLOBAL;
		session.toast(ToastType.INFO, "Toast Message", content);
	}

	@JSFunction
	public String papagoTranslate(String sourceLanguage, String targetLanguage, String data, Boolean errorToString) {
		return null;
	}

	@JSFunction
	public Boolean makeNoti(String title, String content, int id) {
		Session session = Session.GLOBAL;
		session.toast(ToastType.INFO, title, content);
		return true;
	}

	@JSFunction
	public void gc() {
		System.gc();
	}

	@JSFunction("UIThread")
	public void UIThread(Function function, Function onComplete) {
		return ;
	}
}