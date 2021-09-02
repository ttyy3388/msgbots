package org.beuwi.msgbots.actions;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.manager.ProjectManager;

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
