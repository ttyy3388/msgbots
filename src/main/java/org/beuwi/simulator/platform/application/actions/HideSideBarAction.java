package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.parts.ActiveAreaPart;

public class HideSideBarAction
{
	private static AnchorPane pane = null;

	private static boolean isHided = false;

	public static void initialize()
	{
		pane = ActiveAreaPart.getRoot();
	}

	public static void update(boolean hide)
	{
		isHided = hide;

		if (hide)
		{
			pane.setPrefWidth(40);
		}
		// Show
		else
		{
			pane.setPrefWidth(200);
		}
	}

	public static boolean isHided()
	{
		return isHided;
	}
}