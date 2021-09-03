package org.beuwi.msgbots.view.app.actions;

import javafx.scene.Node;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.app.parts.MainAreaPart;
import org.beuwi.msgbots.view.gui.control.TabItem;
import org.beuwi.msgbots.view.gui.control.TabView;
import org.beuwi.msgbots.view.gui.editor.Editor;
import org.beuwi.msgbots.view.gui.layout.DebugPane;

public class SaveOpenedEditorAction implements Executor {
    private static SaveOpenedEditorAction instance = null;

    private final TabView control = MainAreaPart.getInstance().getTabView();

    public void execute() {
        TabItem item = control.getSelectedTab();
        if (item != null) {
            Node content = item.getContent();
            if (content instanceof DebugPane) {
                DebugPane pane = (DebugPane) content;
                Editor editor = pane.getEditor();
                editor.save();
            }
        }
    }

    public static SaveOpenedEditorAction getInstance() {
        if (instance == null) {
            instance = new SaveOpenedEditorAction();
        }
        return instance;
    }
}
