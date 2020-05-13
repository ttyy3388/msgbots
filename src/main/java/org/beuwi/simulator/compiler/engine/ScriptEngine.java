package org.beuwi.simulator.compiler.engine;

import javafx.application.Platform;
import org.beuwi.simulator.compiler.api.Api;
import org.beuwi.simulator.compiler.api.AppData;
import org.beuwi.simulator.compiler.api.Bridge;
import org.beuwi.simulator.compiler.api.DataBase;
import org.beuwi.simulator.compiler.api.Device;
import org.beuwi.simulator.compiler.api.FileStream;
import org.beuwi.simulator.compiler.api.GlobalLog;
import org.beuwi.simulator.compiler.api.ImageDB;
import org.beuwi.simulator.compiler.api.Log;
import org.beuwi.simulator.compiler.api.Replier;
import org.beuwi.simulator.compiler.api.Utils;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.managers.LogManager;
import org.beuwi.simulator.platform.application.views.actions.SaveEditorTabAction;
import org.beuwi.simulator.platform.ui.components.ILogType;
import org.beuwi.simulator.settings.Settings;
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
		LogManager.append("컴파일 시작 : " + name, ILogType.EVENT);

		compiling.put(name, true);

		File file = FileManager.getBotScript(name);

		if (Settings.getPublicSetting("program").getBoolean("autoSave"))
		{
			SaveEditorTabAction.update(name);
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
			script = parseContext.compileString(FileManager.read(file), file.getName(), 0, null);

			int flags = ScriptableObject.EMPTY;

			ScriptableObject.defineProperty(scope, "Api", ScriptUtils.convert(new Api(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "Device", ScriptUtils.convert(new Device(scope)), flags);
			ScriptableObject.defineProperty(scope, "GlobalLog", ScriptUtils.convert(new GlobalLog(scope)), flags);
			ScriptableObject.defineProperty(scope, "Log", ScriptUtils.convert(new Log(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "DataBase", ScriptUtils.convert(new DataBase(scope, name)), flags);
			ScriptableObject.defineProperty(scope, "Utils", ScriptUtils.convert(new Utils(scope, name)), flags);

			ScriptableObject.defineProperty(scope, "Bridge", ScriptUtils.convert(new Bridge(scope)), flags);
			ScriptableObject.defineProperty(scope, "AppData", ScriptUtils.convert(new AppData(scope)), flags);
			ScriptableObject.defineProperty(scope, "FileStream", ScriptUtils.convert(new FileStream(scope)), flags);

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

			LogManager.append(e.toString() + " : " + name, ILogType.ERROR);

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

		LogManager.append("컴파일 완료 : " + name, ILogType.EVENT);

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
					responder.call(context, scope, scope, new Object[]{new ResponseParameters(room, message, sender, isGroupChat, new Replier(), imageDB, packageName)});
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
			LogManager.append(e.toString() + " : " + name, ILogType.ERROR);

			if (Settings.getScriptSetting(name).getBoolean("offOnRuntimeError"))
			{
				BotManager.setPower(name, false);
			}
		}
	}
}
