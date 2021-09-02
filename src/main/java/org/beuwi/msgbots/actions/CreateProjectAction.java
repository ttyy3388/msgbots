package org.beuwi.msgbots.actions;

import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.shared.SharedValues;

import java.io.File;

public class CreateProjectAction implements Executor {
	private static CreateProjectAction instance = null;

    public void execute(String name, boolean isUnified, boolean isOffError) {
        execute(name, null, false, isUnified, isOffError);
    }

	public void execute(String name, String content, boolean isImport, boolean isUnified, boolean isOffError) {
        File folder = new File(SharedValues.getString("path.botFolder") + File.separator + name);
        if (ProjectManager.exists(name)) {
            // DisplayErrorDialogAction.execute(new IOException("Bot " + name + " already exists"));
        }
		/* else if (folder.exists()) {

        } */
        else {
            folder.mkdir();

            // 임폴트가 아닌 봇 생성이라면
            if (!isImport) {
                // 통합된 매개변수 체크
                if (isUnified) {
                    content = SharedValues.getDfile("dfile.scriptUnified").getData();
                } else {
                    content = SharedValues.getDfile("dfile.scriptDefault").getData();
                }
            }

            String path = folder + File.separator;

            JObject config = new JObject();
            config.put("optimization", 1);
            config.put("useUnifiedParams", isUnified);
            config.put("offOnRuntimeError", isOffError);
            config.put("power", false);
            config.put("ignoreApiOff", false);

            FileManager.write(path + "index.js", content); // Create bot script file
            FileManager.write(path + "log.json", "[]"); // Create bot log file
            FileManager.write(path + "bot.json", config.toString()); // Create bot setting file
        }
    }

	public static CreateProjectAction getInstance() {
		if (instance == null) {
			instance = new CreateProjectAction();
		}
		return instance;
	}
}
