package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class ChangeActivityTabAction
{
	// Activity Bar
	private static VBox box;
	private static StackPane pane;

	public static void initialize()
	{
		box = ActiveAreaPart.getActivityBar();
		pane = ActiveAreaPart.getSideBar();
	}

	public static void update(int index)
	{
		ToggleButton toggle = null;

		if (index != 1)
		{
			toggle = (ToggleButton) box.getChildren().get(1);
		}
		else
		{
			toggle = (ToggleButton) box.getChildren().get(0);
		}

		toggle.setSelected(false);

		switch (index)
		{
			// Bots Tab
			case 0 :

				pane.getChildren().get(0).setVisible(true);
				pane.getChildren().get(1).setVisible(false);

				break;

			// Debug Tab
			case 1 :

				pane.getChildren().get(1).setVisible(true);
				pane.getChildren().get(0).setVisible(false);

				break;
		}
	}
}