package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.parts.ActiveAreaPart;

public class HideSideBarAction
{
	private static AnchorPane anpActiveArea = null;

	private static boolean isHided = false;

	public static void initialize()
	{
		anpActiveArea = ActiveAreaPart.getRoot();
	}

	public static void update(boolean hide)
	{
		isHided = hide;

		if (hide)
		{
			anpActiveArea.setPrefWidth(40);
		}
		// Show
		else
		{
			anpActiveArea.setPrefWidth(220);
		}
	}

	public static boolean isHided()
	{
		return isHided;
	}
}