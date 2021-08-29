package org.beuwi.msgbots.program.app.action;

import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.program.app.impl.Executor;

public class DeleteProjectAction implements Executor {
    private static DeleteProjectAction instance = null;
    public boolean execute(String name) {
        if (!ProjectManager.exists(name)) {
            throw new NullPointerException("wrong access");
        }
       return ProjectManager.findByName(name).delete();
    }

    public static DeleteProjectAction getInstance() {
        if (instance == null) {
            instance = new DeleteProjectAction();
        }
        return instance;
    }
}
