package org.beuwi.msgbots.compiler.engine;

import javafx.application.Platform;
import org.beuwi.msgbots.compiler.api.*;
import org.beuwi.msgbots.manager.BotManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.utils.FileUtils;
import org.beuwi.msgbots.platform.app.utils.Settings;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;

import java.io.File;
import java.util.HashMap;

public class ScriptEngine
{
	public static HashMap<String, ScriptContainer> container = new HashMap<>();
	public static HashMap<String, Boolean> compiling = new HashMap<>();
	public static ScriptableObject execScope = null;

	public static void run(String room, String message, String sender, boolean isGroupChat, ImageDB imageDB, String packageName)
	{
		for (String name : FileManager.getBotNames())
		{
			if (!BotManager.getPower(name))
			{
				continue ;
			}
			if (!container.containsKey(name))
			{
				continue ;
			}

			Platform.runLater(() -> callResponder(name, room, message, sender, isGroupChat, imageDB, packageName));
		}
	}

	public static boolean initialize(String name, boolean isManual, boolean ignoreError)
	{
		// LogManager.addEvent("컴파일 시작 : " + name);

		compiling.put(name, true);

		File file = FileManager.getBotScript(name);

		if (Settings.getPublicSetting("program").getBoolean("autoSave"))
		{
			// SaveEditorTabAction.update(name);
		}

		int optimization = Settings.getScriptSetting(name).getInt("optimization");

		Context parseContext = null;

		try
		{
			parseContext = Context.enter();
			parseContext.setWrapFactory(new PrimitiveWrapFactory());
			parseContext.setLanguageVersion(Context.VERSION_ES6);
			parseContext.setOptimizationLevel(optimization);
		}
		catch (Exception e)
		{
			if (!isManual)
			{
				Context.reportError(e.toString());
			}

			return false;
		}

		System.gc();

		ScriptableObject scope = null;
		Script script = null;

		try
		{
			if (container.get(name) != null)
			{
				if (container.get(name).getOnStartCompile() != null)
				{
					container.get(name).getOnStartCompile().call(parseContext, execScope, execScope, new Object[] { });
				}
			}

			scope = (ScriptableObject) parseContext.initStandardObjects(new ImporterTopLevel(parseContext));
			script = parseContext.compileString(FileUtils.read(file), file.getName(), 0, null);

			int flags = ScriptableObject.EMPTY;

			ScriptableObject.defineProperty(scope, "Api", new Api(scope, name), flags);
			ScriptableObject.defineProperty(scope, "Device", new Device(scope), flags);
			ScriptableObject.defineProperty(scope, "GlobalLog", new GlobalLog(scope), flags);
			ScriptableObject.defineProperty(scope, "Log", new Log(scope, name), flags);
			ScriptableObject.defineProperty(scope, "DataBase", new DataBase(scope, name), flags);
			ScriptableObject.defineProperty(scope, "Utils", new Utils(scope, name), flags);

			ScriptableObject.defineClass(scope, AppData.class);
			ScriptableObject.defineClass(scope, Bridge.class);
			ScriptableObject.defineClass(scope, FileStream.class);

			execScope = scope;

			script.exec(parseContext, scope);

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
		catch (Throwable e)
		{
			if (container.get(name) != null)
			{
				container.get(name).setOnStartCompile(null);
			}

			// LogManager.addError(e.toString() + " : " + name);

			compiling.put(name, false);

			if (!isManual)
			{
				if (!ignoreError)
				{
					Context.reportError(e.toString());
				}
			}

			e.printStackTrace();

			return false;
		}

		BotManager.setCompiled(name, true);

		// LogManager.addEvent("컴파일 완료 : " + name);

		return true;
	}

	public static void callResponder(String name, String room, String message, String sender, Boolean isGroupChat, ImageDB imageDB, String packageName)
	{
		ScriptableObject scope = container.get(name).getExecScope();
		Function responder = container.get(name).getResponder();

		try
		{
			Context context = Context.enter();
			context.setWrapFactory(new PrimitiveWrapFactory());
			context.setLanguageVersion(Context.VERSION_ES6);
			context.setOptimizationLevel(container.get(name).getOptimization());

			if (responder != null)
			{
				if (Settings.getScriptSetting(name).getBoolean("useUnifiedParams"))
				{
					responder.call(context, scope, scope, new Object[] { new ResponseParameters(room, message, sender, isGroupChat, new Replier(), imageDB, packageName) });
				}
				else
				{
					responder.call(context, scope, scope, new Object[] { room, message, sender, isGroupChat, new Replier(), imageDB, packageName });
				}
			}

			Context.exit();
		}
		catch (Throwable e)
		{
			// LogManager.addError(e.toString() + " : " + name);

			if (Settings.getScriptSetting(name).getBoolean("offOnRuntimeError"))
			{
				BotManager.setPower(name, false);
			}
		}
	}
}
