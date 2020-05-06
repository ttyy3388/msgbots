package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;

import java.util.List;

public class SaveAllEditorTabsAction
{
	public static void update()
	{
		List<IEditorPane> panes = EditorAreaPart.getEditors();

		for (IEditorPane pane : panes)
		{
			SaveEditorTabAction.update(pane.getTabPane().getTabs());
		}
	}
}