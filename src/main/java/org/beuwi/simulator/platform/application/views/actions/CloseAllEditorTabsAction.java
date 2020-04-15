package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;

public class CloseAllEditorTabsAction
{
	public static void update()
	{
		CloseEditorTabAction.update(EditorAreaPart.getSelectedPane().getTabs());
	}
}