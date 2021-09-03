package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.compiler.api.ImageDB;
import org.beuwi.msgbots.compiler.engine.ScriptEngine;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.setting.GlobalSettings;

public class ScriptManager extends ScriptEngine implements Manager {
	public static void run(String message) {
		ScriptEngine.run(
			ProjectManager.getList(),
			message,
			GlobalSettings.getString("debug.roomName"),
			GlobalSettings.getString("debug.senderName"),
			GlobalSettings.getBoolean("debug.isGroupChat"),
			new ImageDB(),
			GlobalSettings.getString("debug.packageName")
		);
	}

	public static void run(Project project, String message) {
		ScriptEngine.run(
			project,
			message,
			GlobalSettings.getString("debug.roomName"),
			GlobalSettings.getString("debug.senderName"),
			GlobalSettings.getBoolean("debug.isGroupChat"),
			new ImageDB(),
			GlobalSettings.getString("debug.packageName")
		);
	}

	public static void preInit() {
		if (!GlobalSettings.getBoolean("program.startAutoCompile")) {
			return ;
		}

		for (Project project : ProjectManager.getList()) {
			if (!project.getPower()) {
				continue ;
			}
			ScriptEngine.initialize(project, true, false);
		}
	}

	public static void initAll(boolean isManual) {
		for (Project project : ProjectManager.getList()) {
			ScriptEngine.initialize(project, isManual, true);
		}
	}

	public static boolean initScript(Project project, boolean isManual, boolean ignoreError) {
		return ScriptEngine.initialize(project, isManual, ignoreError);
	}
}
