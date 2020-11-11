package org.beuwi.msgbots.platform.app.view.parts;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.VBox;
import org.beuwi.msgbots.platform.gui.layout.BorderPanel;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

public class MainAreaPart implements View
{
    private static final StackPanel root = new StackPanel();
    private static final BorderPanel pane = new BorderPanel();

    {
        VBox.setVgrow(getRoot(), Priority.ALWAYS);
    }

    @Override
    public void init()
    {
        pane.setLeft(SideAreaPart.getRoot());
        pane.setRight(DebugAreaPart.getRoot());
        pane.setCenter(EditorAreaPart.getRoot());

        root.addItem(MainAreaPart.getContent());
        root.addItem(NoticeAreaPart.getRoot());
    }

    public static StackPanel getRoot()
    {
        return root;
    }

    public static BorderPane getContent()
    {
        return pane;
    }
}