package org.beuwi.msgbots.view.app.actions;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.manager.ScriptManager;

public class ReloadAllBotsAction implements Executor {
	private static ReloadAllBotsAction instance = null;

	public void execute() {
		ScriptManager.initAll(true);
	}

	public static ReloadAllBotsAction getInstance() {
		if (instance == null) {
			instance = new ReloadAllBotsAction();
		}
		return instance;
	}
}
