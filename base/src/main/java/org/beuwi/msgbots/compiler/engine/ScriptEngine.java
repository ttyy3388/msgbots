package org.beuwi.msgbots.compiler.engine;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.Session;
import org.beuwi.msgbots.base.type.LogType;
import org.beuwi.msgbots.compiler.api.ImageDB;
import org.beuwi.msgbots.base.Logger;
import org.beuwi.msgbots.compiler.api.Replier;

import org.beuwi.msgbots.setting.ProjectSettings;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;

import java.util.HashMap;
import java.util.List;

public class ScriptEngine {
	protected static final HashMap<String, ScriptContainer> container = new HashMap<>();
	protected static final  HashMap<String, Boolean> compiling = new HashMap<>();
	protected static ScriptableObject execscope = null;

	protected static final Logger logger = Session.GLOBAL.getLogger();

	// 전역 디버그 룸에서 호출
	protected static void run(List<Project> list, String message, String room, String sender, boolean isGroupChat, ImageDB imageDB, String packageName) {
		for (Project project : list) {
			if (!project.getPower()) {
				continue ;
			}
			if (!container.containsKey(project.getName())) {
				continue ;
			}
			callResponder(true, project, message,  room, sender, isGroupChat, imageDB, packageName);
		}
	}

	// 개별 디버그 룸에서 호출
	protected static void run(Project project, String message, String room, String sender, boolean isGroupChat, ImageDB imageDB, String packageName) {
		if (!project.getPower()) {
			return;
		}
		if (!container.containsKey(project.getName())) {
			return;
		}
		callResponder(false, project, message,  room, sender, isGroupChat, imageDB, packageName);
	}

	protected static boolean initialize(Project project, boolean isManual, boolean ignoreError) {
		// final Session session = Session.GLOBAL;
		final String name = project.getName();
		// final Logger logger = session.getLogger();

		compiling.put(name, true);

		int optimization = 1;

		// Parse Context
		Context context = Context.enter();

		try {
			context.setWrapFactory(new PrimitiveWrapFactory());
			context.setLanguageVersion(Context.VERSION_ES6);
			context.setOptimizationLevel(optimization);
		}
		catch (Exception e) {
			if (!isManual) {
				Context.reportError(e.toString());
			}
			return false;
		}

		System.gc();

		ScriptableObject scope = null;
		Script script = null;

		try {
			if (container.get(name) != null) {
				if (container.get(name).getOnStartCompile() != null) {
					container.get(name).getOnStartCompile().call(context, execscope, execscope, new Object[] { });
				}
			}

			scope = (ScriptableObject) context.initStandardObjects(new ImporterTopLevel(context));
			script = context.compileString(project.getScript(), name, 0, null);

			int flags = ScriptableObject.EMPTY;

			/* ScriptableObject.defineProperty(scope, "Api", 		ScriptUtils.convert(new Api(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "Device", 	ScriptUtils.convert(new Device(scope)), flags);
			ScriptableObject.defineProperty(scope, "GlobalLog", ScriptUtils.convert(new GlobalLog(scope)), flags);
			ScriptableObject.defineProperty(scope, "Log", 		ScriptUtils.convert(new Log(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "DataBase",  ScriptUtils.convert(new DataBase(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "Utils", 	ScriptUtils.convert(new Utils(scope)), flags);

			ScriptableObject.defineClass(scope, AppData.class);
			ScriptableObject.defineClass(scope, Bridge.class);
			ScriptableObject.defineClass(scope, FileStream.class); */

			execscope = scope;

			script.exec(context, scope);

			Function onStartCompile = scope.has("onStartCompile", scope) ? (Function) scope.get("onStartCompile", scope) : null;
			Function responder = scope.has("response", scope) ? (Function) scope.get("response", scope) : null;

			container.put(name, new ScriptContainer()
					.setExecScope(scope)
					.setResponder(responder)
					.setOnStartCompile(onStartCompile)
					.setOptimization(optimization)
					.setScope(scope));

			Context.exit();

			compiling.put(name, false);
		}
		catch (Throwable e) {
			if (container.get(name) != null) {
				container.get(name).setOnStartCompile(null);
			}

			compiling.put(name, false);

			if (!isManual) {
				if (!ignoreError) {
					Context.reportError(e.toString());
				}
			}

			logger.error("Compile Error: " + e.toString() + " : " + name);

			return false;
		}

		project.setCompiled(true);

		logger.info("Compile Complete: " + name);

		return true;
	}

	// global: 전역 디버그 룸(true), 개별 디버그 룸(false) 구별
	protected static void callResponder(boolean global, Project project, String message, String room, String sender, Boolean isGroupChat, ImageDB imageDB, String packageName) {
		String name = project.getName();
		ScriptableObject scope = container.get(name).getExecScope();
		Function responder = container.get(name).getResponder();
		Context context = Context.enter();

		// 전역 디버그 룸에서 호출 시 글로벌 세션 삽입
		Session session = global ? Session.GLOBAL : project.getSession();
		Replier replier = new Replier(session);

		try {
			context.setWrapFactory(new PrimitiveWrapFactory());
			context.setLanguageVersion(Context.VERSION_ES6);
			context.setOptimizationLevel(container.get(name).getOptimization());

			if (responder != null) {
				if (ProjectSettings.get(project).getBoolean("useUnifiedParams")) {
					responder.call(context, scope, scope, new Object[] { new ResponseParameters(room, message, sender, isGroupChat, replier, imageDB, packageName) });
				}
				else {
					responder.call(context, scope, scope, new Object[] { room, message, sender, isGroupChat, replier, imageDB, packageName });
				}
			}

			Context.exit();
		}
		catch (Throwable e) {
			if (ProjectSettings.get(project).getBoolean("offOnRuntimeError")) {
				project.setPower(false);
			}
			logger.error("Runtime Error: " + e.toString() + " : " + name);
		}
	}
}
