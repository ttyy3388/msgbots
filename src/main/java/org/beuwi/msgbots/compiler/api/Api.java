package org.beuwi.msgbots.compiler.api;

import javafx.application.Platform;

import org.beuwi.msgbots.compiler.engine.ScriptEngine;
import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.manager.BotManager;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.ShowWinMsgAction;
import org.beuwi.msgbots.platform.app.view.actions.ShowToastMessageAction;
import org.beuwi.msgbots.platform.gui.control.ToastItem;
import org.beuwi.msgbots.platform.gui.enums.ToastType;
import org.beuwi.msgbots.setting.ScriptSettings;

import org.mozilla.javascript.*;
import org.mozilla.javascript.annotations.JSFunction;

// 해당 클래스는 해당 패키지(org.beuwi.msgbots.compiler)의 내부에서만 접근해야 함
public class Api extends ScriptableObject {
	@Override
	public String getClassName() {
		return "Api";
	}

	final String name;

	public Api(ScriptableObject object, String name) {
		super(object, object.getPrototype());

		this.name = name;
	}

	@JSFunction
	public Object getContext() {
		return null;
	}

	@JSFunction
	public Boolean on(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			FileManager.getBotNames().forEach(botName ->
				BotManager.setPower(botName, true)
			);
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			try {
				BotManager.setPower(scriptName, true);
			}
			catch (Exception e) {
				return false;
			}
		}

		return true;
	}
	
	@JSFunction
	public Boolean off(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			FileManager.getBotNames().forEach(botName ->
				BotManager.setPower(botName, false)
			);
		}
		else {
			String scriptName = Utils.toScriptName(inputName);
			// Api Off 설정이 켜져 있으면 무시
			if (ScriptSettings.get(scriptName).getBoolean("ignore_api_off")) {
				return false;
			}

			try {
				BotManager.setPower(scriptName, false);
			}
			catch (Exception e) {
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
			if (!FileManager.getBotScript(scriptName).exists()) {
				return false;
			}

			return ScriptManager.initScript(scriptName, false, !stopOnError);
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
			for (String botName : FileManager.getBotNames()) {
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
			if (ScriptManager.container.get(scriptName) != null) {
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
			return BotManager.getPower(Utils.toScriptName(inputName));
		}
	}

	@JSFunction
	public Boolean isCompiled(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			return false;
		}
		else {
			return BotManager.isCompiled(Utils.toScriptName(inputName));
		}
	}

	@JSFunction
	public Boolean isCompiling(String inputName) {
		if (Undefined.isUndefined(inputName)) {
			for (String botName : FileManager.getBotNames()) {
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
				ScriptEngine.execScope,
				FileManager.getBotNames().toArray()
		);
	}

	@JSFunction
	public Boolean replyRoom(String room, String message, Boolean hideToast) {
		Replier.reply(message);
		return true;
	}

	@JSFunction
	public Boolean canReply(String room) {
		return true;
	}

	@JSFunction
	public void showToast(String content, int length) {
		Platform.runLater(() -> ShowToastMessageAction.execute(new ToastItem(
			ToastType.EVENT, "Toast Message", content
		)));
	}

	@JSFunction
	public String papagoTranslate(String sourceLanguage, String targetLanguage, String data, Boolean errorToString) {
		return null;
	}

	@JSFunction
	public Boolean makeNoti(String title, String content, int id) {
		ShowWinMsgAction.execute(title, content);
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