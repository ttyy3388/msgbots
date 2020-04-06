package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.tabs.GlobalLogTab;
import org.beuwi.simulator.platform.ui.components.ITabType;

public class OpenGlobalLogTabAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = GlobalLogTab.getRoot();
	}

	public static void update()
	{
		AddEditorTabAction.update("Global Log", pane, ITabType.LOG);
	}
}