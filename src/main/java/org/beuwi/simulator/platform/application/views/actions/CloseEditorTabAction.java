package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class CloseEditorTabAction
{
	private static ITabPane pane;

	public static void initialize()
	{
		pane = EditorAreaPart.getComponent();
	}

	public static void update(Tab tab)
	{
		pane.closeTab(tab);
	}
}