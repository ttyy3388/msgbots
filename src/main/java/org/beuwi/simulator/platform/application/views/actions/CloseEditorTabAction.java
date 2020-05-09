package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;

import java.util.Collection;

public class CloseEditorTabAction
{
	public static void update()
	{
		update(EditorAreaPart.getSelectedPane());
	}

	public static void update(IEditorPane pane)
	{
		pane.closeTab(pane.getSelectedTab());
	}

	public static void update(Collection tabs)
	{
		EditorAreaPart.getSelectedPane().getTabs().removeAll(tabs);
	}
}