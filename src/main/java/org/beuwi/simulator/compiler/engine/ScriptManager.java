package org.beuwi.simulator.compiler.engine;

import javafx.application.Platform;
import org.beuwi.simulator.compiler.api.*;
import org.beuwi.simulator.managers.BotManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.actions.SaveEditorTabAction;
import org.beuwi.simulator.platform.application.actions.SendChatMessageAction;
import org.beuwi.simulator.settings.Settings;
import org.mozilla.javascript.*;
import org.mozilla.javascript.annotations.JSStaticFunction;

import java.io.File;
import java.util.HashMap;

public class ScriptManager
{
	public static HashMap<String, ScriptContainer> container = new HashMap<>();
	public static HashMap<String, Boolean> compiling = new HashMap<>();
	public static ScriptableObject execScope = null;

	public static void run(String message)
	{
		String  room 		= Settings.getPublicSetting("chat").getString("room");
		String  sender 		= Settings.getPublicSetting("chat").getString("sender");
		boolean isGroupChat = Settings.getPublicSetting("chat").getBoolean("isGroupChat");
		String  packageName = Settings.getPublicSetting("chat").getString("package");

		run(room, message, sender, isGroupChat, new ImageDB(), packageName);
	}

	private static void run(String room, String message, String sender, boolean isGroupChat, ImageDB imageDB, String packageName)
	{
		for (String name : FileManager.getBotNames())
		{
			if (!Settings.getScriptSetting(name).getBoolean("power"))
			{
				continue ;
			}
			if (!container.containsKey(name))
			{
				continue ;
			}
			else
			{
				Platform.runLater(() -> callResponder(name, room, message, sender, isGroupChat, imageDB, packageName));
			}
		}
	}

	public static boolean setInitialize(String name, boolean isManual, boolean ignoreError)
	{
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

			ScriptableObject.defineClass(scope, Api.class);
			ScriptableObject.defineClass(scope, AppData.class);
			ScriptableObject.defineClass(scope, Bridge.class);
			ScriptableObject.defineClass(scope, DataBase.class);
			ScriptableObject.defineClass(scope, Device.class);
			ScriptableObject.defineClass(scope, FileStream.class);
			ScriptableObject.defineClass(scope, Log.class);
			ScriptableObject.defineClass(scope, Utils.class);

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

			// LogService.addError(name, e.toString());

			compiling.put(name, false);

			if (!isManual)
			{
				if (!ignoreError)
				{
					Context.reportError(e.toString());
				}
			}

			return false;
		}

		BotManager.setBotCompiled(name, true);

		return true;
	}

	public static void allInitialize(boolean isManual)
	{
		for (String name : FileManager.getBotNames())
		{
			setInitialize(name, isManual, true);
		}
	}

	public static void preInitialize()
	{
		if (Settings.getPublicSetting("program").getBoolean("autoCompile"))
		{
			for (String name : FileManager.getBotNames())
			{
				if (Settings.getScriptSetting(name).getBoolean("power"))
				{
					setInitialize(name, true, false);
				}
			}
		}
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
			// LogService.addError(name, e.toString());

			if (Settings.getScriptSetting(name).getBoolean("offOnRuntimeError"))
			{
				BotManager.setBotPower(name, false);
			}
		}
	}

	public static class Replier
	{
		@JSStaticFunction
		public static Boolean reply(String message)
		{
			SendChatMessageAction.update(message, true);

			return true;
		}

		@JSStaticFunction
		public static Boolean reply(String room, String message, Boolean hideToast)
		{
			SendChatMessageAction.update(message, true);

			return true;
		}
	}
}
