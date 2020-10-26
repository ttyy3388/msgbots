package org.beuwi.msgbots.platform.app.view.parts;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class MainAreaPart implements View
{
    private static final StackPane root = new StackPane();
    private static final BorderPane pane = new BorderPane();

    {
        VBox.setVgrow(getRoot(), Priority.ALWAYS);
    }

    @Override
    public void init()
    {
        pane.setLeft(SideAreaPart.getRoot());
        pane.setRight(DebugAreaPart.getRoot());
        pane.setCenter(EditorAreaPart.getRoot());

        root.setContent(MainAreaPart.getContent());
    }

    public static StackPane getRoot()
    {
        return root;
    }

    public static BorderPane getContent()
    {
        return pane;
    }
}