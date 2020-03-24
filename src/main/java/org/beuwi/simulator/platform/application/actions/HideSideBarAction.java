package org.beuwi.simulator.platform.application.actions;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.application.views.parts.ActivityBarPart;
import org.beuwi.simulator.platform.application.views.parts.SideBarPart;

public class HideSideBarAction
{
	private static HBox 	  hoxActiveArea;
	private static VBox 	  voxActivityBar;
	private static AnchorPane anpSideBar;

	private static boolean isHided = false;

	public static void initialize()
	{
		hoxActiveArea  = (HBox) ActiveAreaPart.getNameSpace().get("hoxActiveArea");
		voxActivityBar = (VBox) ActivityBarPart.getNameSpace().get("voxActivityBar");
		anpSideBar     = SideBarPart.getRoot();
	}

	public static void update(boolean hide)
	{
		if (hide)
		{
			hoxActiveArea.getChildren().remove(anpSideBar);
		}
		else
		{
			hoxActiveArea.getChildren().add(anpSideBar);
		}

		isHided = hide;
	}

	public static boolean isHided()
	{
		return isHided;
	}
}