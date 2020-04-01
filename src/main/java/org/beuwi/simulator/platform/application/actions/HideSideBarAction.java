package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class HideSideBarAction
{
	private static AnchorPane pane;

	private static boolean isHided;

	public static void initialize()
	{
		pane = ActiveAreaPart.getRoot();
	}

	public static void update(boolean hide)
	{
		if (hide)
		{
			pane.setPrefWidth(44);
		}
		else
		{
			pane.setPrefWidth(220);
		}

		isHided = hide;
	}

	public static boolean isHided()
	{
		return isHided;
	}
}