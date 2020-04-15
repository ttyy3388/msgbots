package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.IPos;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;
import org.beuwi.simulator.platform.ui.editor.IEditorTab;

import java.util.List;

public class MoveEditorPaneAction
{
	private static SplitPane pane;

	public static void initialize()
	{
		pane = EditorAreaPart.getComponent();
	}

	public static void update(IEditorTab tab, IPos pos)
	{
		// IEditor Pane
		List<Node> panes = pane.getItems();

		IEditorPane editor = tab.getEditorPane();

		int index = panes.indexOf(editor);

		switch (pos)
		{
            case RIGHT :

				if (index < panes.size())
				{
					editor.removeTab(tab);
                    ((IEditorPane) panes.get(index + 1)).addTab(tab);
				}

				break;

            case LEFT :

			    if (index != 0)
                {
                    editor.removeTab(tab);
                    ((IEditorPane) panes.get(index - 1)).addTab(tab);
                }

				break;
		}
	}
}