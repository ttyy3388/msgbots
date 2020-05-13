package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class HideSideBarAction
{
	private static HBox hbox;
	private static VBox vbox;
	private static StackPane pane;

	private static ToggleButton saved;
	private static boolean hided;

	public static void initialize()
	{
		hbox = ActiveAreaPart.getComponent();
		vbox = ActiveAreaPart.getActivityBar();
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

		hided = hide;
	}

	public static boolean isHided()
	{
		return hided;
	}
}