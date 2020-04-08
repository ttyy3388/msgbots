package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class HideSideBarAction
{
	private static HBox hbox;
	private static AnchorPane pane;

	private static boolean isHided;

	public static void initialize()
	{
		hbox = ActiveAreaPart.getComponent();
		pane = ActiveAreaPart.getSideBar();
	}

	public static void update(boolean hide)
	{
		if (hide)
		{
			hbox.getChildren().remove(pane);
		}
		else
		{
			hbox.getChildren().add(pane);
		}

		isHided = hide;
	}

	public static boolean isHided()
	{
		return isHided;
	}
}