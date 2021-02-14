package org.beuwi.msgbots.compiler.engine;

import org.beuwi.msgbots.compiler.api.Api;
import org.beuwi.msgbots.compiler.api.AppData;
import org.beuwi.msgbots.compiler.api.Bridge;
import org.beuwi.msgbots.compiler.api.DataBase;
import org.beuwi.msgbots.compiler.api.Device;
import org.beuwi.msgbots.compiler.api.FileStream;
import org.beuwi.msgbots.compiler.api.GlobalLog;
import org.beuwi.msgbots.compiler.api.ImageDB;
import org.beuwi.msgbots.compiler.api.Log;
import org.beuwi.msgbots.compiler.api.Replier;
import org.beuwi.msgbots.compiler.api.Utils;
import org.beuwi.msgbots.manager.BotManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorToastAction;
import org.beuwi.msgbots.platform.app.view.actions.InputDetailLogAction;
import org.beuwi.msgbots.platform.app.view.actions.SaveBotScriptTabAction;
import org.beuwi.msgbots.platform.app.view.actions.ShowToastMessageAction;
import org.beuwi.msgbots.platform.gui.control.ToastItem;
import org.beuwi.msgbots.platform.gui.enums.ToastType;
import org.beuwi.msgbots.setting.GlobalSettings;
import org.beuwi.msgbots.setting.ScriptSettings;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;

import java.io.File;
import java.util.HashMap;

public class ScriptEngine {
	public static HashMap<String, ScriptContainer> container = new HashMap<>();
	public static HashMap<String, Boolean> compiling = new HashMap<>();
	public static ScriptableObject execScope = null;

	protected static void run(String room, String message, String sender, boolean isGroupChat, ImageDB imageDB, String packageName) {
		for (String name : FileManager.getBotNames()) {
			if (!BotManager.getPower(name)) {
				continue ;
			}

			if (!container.containsKey(name)) {
				continue ;
			}

			new Thread(() -> {
				callResponder(name, room, message, sender, isGroupChat, imageDB, packageName);
			}).start();
		}
	}

	protected static boolean initialize(String name, boolean isManual, boolean ignoreError) {
		InputDetailLogAction.execute("Compile Start", name);

		final long start = System.currentTimeMillis();

		compiling.put(name, true);

		File file = FileManager.getBotScript(name);

		/* if (file == null) {
			new Exception("File must not be null");
		} */

		if (GlobalSettings.getBoolean("program:compile_auto_save")) {
			SaveBotScriptTabAction.execute(name);
		}

		int optimization = ScriptSettings.get(name).getInt("optimization");

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

			DisplayErrorToastAction.execute(e);

			return false;
		}

		System.gc();

		ScriptableObject scope = null;
		Script script = null;

		try {
			if (container.get(name) != null) {
				if (container.get(name).getOnStartCompile() != null) {
					container.get(name).getOnStartCompile().call(context, execScope, execScope, new Object[] { });
				}
			}

			scope = (ScriptableObject) context.initStandardObjects(new ImporterTopLevel(context));

			script = context.compileString(FileManager.read(file), file.getName(), 0, null);

			int flags = ScriptableObject.EMPTY;

			ScriptableObject.defineProperty(scope, "Api", 		 ScriptUtils.convert(new Api(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "Device", 	 ScriptUtils.convert(new Device(scope)), flags);
			ScriptableObject.defineProperty(scope, "GlobalLog", ScriptUtils.convert(new GlobalLog(scope)), flags);
			ScriptableObject.defineProperty(scope, "Log", 		 ScriptUtils.convert(new Log(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "DataBase",  ScriptUtils.convert(new DataBase(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "Utils", 	 ScriptUtils.convert(new Utils(scope, name)), flags);

			ScriptableObject.defineClass(scope, AppData.class);
			ScriptableObject.defineClass(scope, Bridge.class);
			ScriptableObject.defineClass(scope, FileStream.class);

			execScope = scope;

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

			ShowToastMessageAction.execute(
				new ToastItem(
					ToastType.ERROR,
					"Compile Error : " + name,
					e.toString()
				)
			);

			LogManager.error("Compile Error : " + e.toString() + " : " + name);

			return false;
		}

		final long end = System.currentTimeMillis();

		BotManager.setCompiled(name, true);

		InputDetailLogAction.execute("Compile Complete", name + " (" + (end - start) + ".ms)");

		LogManager.event("Compile Complete: " + name);

		return true;
	}

	protected static void callResponder(String name, String room, String message, String sender, Boolean isGroupChat, ImageDB imageDB, String packageName) {
		final long start = System.currentTimeMillis();

		ScriptableObject scope = container.get(name).getExecScope();
		Function responder = container.get(name).getResponder();

		Context context = Context.enter();

		try {
			context.setWrapFactory(new PrimitiveWrapFactory());
			context.setLanguageVersion(Context.VERSION_ES6);
			context.setOptimizationLevel(container.get(name).getOptimization());

			if (responder != null) {
				if (ScriptSettings.get(name).getBoolean("use_unified_params")) {
					responder.call(context, scope, scope, new Object[] { new ResponseParameters(room, message, sender, isGroupChat, new Replier(), imageDB, packageName) });
				}
				else {
					responder.call(context, scope, scope, new Object[] { room, message, sender, isGroupChat, new Replier(), imageDB, packageName });
				}
			}

			Context.exit();
		}
		catch (Throwable e) {
			if (ScriptSettings.get(name).getBoolean("off_on_runtime_error")) {
				BotManager.setPower(name, false);
			}

			LogManager.error("Runtime Error : " + e.toString() + " : " + name);
		}

		final long end = System.currentTimeMillis();

		if (GlobalSettings.getBoolean("debug:show_running_time")) {
			InputDetailLogAction.execute("Running Time", name + " (" + (end - start) + ".ms)");
		}
	}
}
