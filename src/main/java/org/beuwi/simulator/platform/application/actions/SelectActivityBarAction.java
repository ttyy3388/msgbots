package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ToggleButton;
import org.beuwi.simulator.platform.application.views.parts.ActivityBarPart;

public class SelectActivityBarAction
{
	private static ToggleButton tgnExplorer;
	private static ToggleButton tgnDebug;

	public static void initialize()
	{
		tgnExplorer = (ToggleButton) ActivityBarPart.getNameSpace().get("tgnExplorer");
		tgnDebug    = (ToggleButton) ActivityBarPart.getNameSpace().get("tgnDebug");
	}

	// Selected Toggle Button Index
	public static void update(int index)
	{
		ToggleButton target = index == 0 ? tgnExplorer :  tgnDebug;

		if (target.isSelected())
		{
			HideSideBarAction.update(true);
		}
		else
		{
			HideSideBarAction.update(false);
		}
	}
}