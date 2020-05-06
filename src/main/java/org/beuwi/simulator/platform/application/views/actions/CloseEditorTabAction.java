package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorTabPane;

import java.util.Collection;

public class CloseEditorTabAction
{
	public static void update()
	{
		update(EditorAreaPart.getSelectedTabPane());
	}

	public static void update(IEditorTabPane pane)
	{
		pane.closeTab(pane.getSelectedTab());
	}

	public static void update(Collection tabs)
	{
		EditorAreaPart.getSelectedTabPane().getTabs().removeAll(tabs);
	}
}