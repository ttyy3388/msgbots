package org.beuwi.msgbots.view.app.actions;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.view.gui.editor.Editor;
import org.beuwi.msgbots.view.gui.layout.DebugPane;
import org.beuwi.msgbots.view.util.GUIManager;

import java.util.List;

public class SaveAllEditorsAction implements Executor {
    private static SaveAllEditorsAction instance = null;

    public void execute() {
        final List<Project> list = ProjectManager.getList();
        list.forEach(project -> {
            DebugPane pane = GUIManager.toViewPane(project);
            assert pane != null;
            Editor editor = pane.getEditor();
            editor.save();
        });
    }

    public static SaveAllEditorsAction getInstance() {
        if (instance == null) {
            instance = new SaveAllEditorsAction();
        }
        return instance;
    }
}
