package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.views.parts.SideBarPart;

public class HideSideBarAction
{
	private static AnchorPane pane;

	private static boolean isHided = false;

	public static void initialize()
	{
		pane = SideBarPart.getRoot();
	}

	public static void update(boolean hide)
	{
		isHided = hide;

		if (hide)
		{
			pane.setDisable(true);
			pane.setVisible(false);
			pane.setPrefWidth(0);
		}
		else
		{
			pane.setPrefWidth(200);
			pane.setVisible(true);
			pane.setDisable(false);
		}
	}

	public static boolean isHided()
	{
		return isHided;
	}
}