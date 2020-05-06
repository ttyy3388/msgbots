package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.SplitPane;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;

public class DeleteEditorPaneAction
{
    private static SplitPane pane;

    public static void initialize()
    {
        pane = EditorAreaPart.getComponent();
    }

    public static void update(IEditorPane editor)
    {
        pane.getItems().remove(editor);
    }
}