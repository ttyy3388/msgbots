package org.beuwi.msgbots.actions;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.shared.SharedValues;

import java.io.File;

public class RenameProjectAction implements Executor {
    private static RenameProjectAction instance = null;

    public boolean execute(String before, String after) {
        // 변경 전과 변경 후가 같으면
        if (before.equals(after)) {
            return false;
        }
        // BEFORE 프로젝트가 존재해야 진행
        if (!ProjectManager.exists(before)) {
            throw new NullPointerException("wrong access");
        }
        // AFTER 프로젝트는 존재하면 안됨
        if (ProjectManager.exists(after)) {
            // DisplayErrorDialogAction.execute(new IOException("already exists"));
        }
        String path = SharedValues.getString("path.botFolder") + File.separator;
        File beforeFile = new File(path + before);
        File afterFile = new File(path + after);
        return beforeFile.renameTo(afterFile);
    }

    public static RenameProjectAction getInstance() {
        if (instance == null) {
            instance = new RenameProjectAction();
        }
        return instance;
    }
}
