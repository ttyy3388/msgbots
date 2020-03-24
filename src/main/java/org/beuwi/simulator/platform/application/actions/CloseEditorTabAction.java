package org.beuwi.simulator.platform.application.actions;

import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ITab;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class CloseEditorTabAction
{
	private static ITabPane pane;

	public static void initialize()
	{
		pane = (ITabPane) EditorAreaPart.getNameSpace().get("tapEditorArea");
	}

	public static void update(ITab tab)
	{
		pane.getTabs().remove(tab);
	}
}