package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class SelectActivityBarAction
{
	private static AnchorPane pane;

	public static void initialize()
	{
		pane = (AnchorPane) ActiveAreaPart.getNameSpace().get("anpActiveArea");
	}

	public static void update()
	{
		if (HideSideBarAction.isHided())
		{
			HideSideBarAction.update(false);
		}
		else
		{
			HideSideBarAction.update(true);
		}
	}
}