package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;

import java.util.List;

public class SaveAllEditorTabsAction
{
	private static SplitPane pane;

	public static void initialize()
	{
		pane = EditorAreaPart.getComponent();
	}

	public static void update()
	{
		List<Node> nodes = pane.getItems();

		for (Node node : nodes)
		{
			IEditorPane editor = (IEditorPane) node;

			SaveEditorTabAction.update(editor.getTabList());
		}
	}
}