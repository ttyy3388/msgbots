package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;
import java.io.IOException;

public class CreateBotAction implements Action {
	public static void execute(String name, boolean isUnified, boolean isOffError) {
		execute(name, null, false, isUnified, isOffError);
	}

	public static void execute(String name, String content, boolean isImport, boolean isUnified, boolean isOffError) {
		File project = FileManager.getBotFolder(name);

		if (project.exists()) {
			DisplayErrorDialogAction.execute(new IOException("Bot " + name + " already exists"));
		}
		else {
			project.mkdir();

			if (!isImport) {
				if (!isUnified) {
					content = FileManager.read(SharedValues.SCRIPT_DEFAULT_FILE);
				}
				else {
					content = FileManager.read(SharedValues.SCRIPT_UNIFIED_FILE);
				}
			}

			// Create bot script file
			FileManager.save(FileManager.getBotScript(name), content);
			// Create bot log file
			FileManager.save(FileManager.getBotLog(name), "[]");
			// Create bot setting file
			FileManager.save
			(
				FileManager.getBotSetting(name),
				"{\"optimization\":1," +
				"\"use_unified_params\":" + isUnified + "," +
				"\"off_on_runtime_error\":" + isOffError + "," +
				"\"power\":false," +
				"\"ignore_api_off\":false}"
			);
		}
	}

	@Override
	public String getName() {
		return "create.bot.action";
	}
}
