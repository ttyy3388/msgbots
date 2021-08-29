package org.beuwi.msgbots.program.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.program.gui.control.TabView;

public class SaveAllEditorsAction implements Executor {
    private static SaveAllEditorsAction instance = null;

    private final TabView control = MainAreaPart.getInstance().getTabView();

    public void execute() {
        /* final List<TabItem> list = control.getTabs();
        if (list != null) {
            for
            Node content = item.getContent();
            if (content instanceof DebugPane) {
                DebugPane pane = (DebugPane) content;
                Editor editor = pane.getEditor();
                File file = editor.getFile();
                String text = editor.getText();
                if (file != null && text != null) {
                    FileManager.write(file, text);
                }
            }
        } */
    }
    public static SaveAllEditorsAction getInstance() {
        if (instance == null) {
            instance = new SaveAllEditorsAction();
        }
        return instance;
    }
}
