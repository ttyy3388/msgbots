package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.views.parts.ActiveAreaPart;

public class ChangeActivityTabAction
{
	// Activity Bar
	private static VBox vbox;

	private static AnchorPane pane;

	public static void initialize()
	{
		vbox = ActiveAreaPart.getActivityBar();
		pane = ActiveAreaPart.getSideBar();
	}

	public static void update(int index)
	{
		((ToggleButton) vbox.getChildren().get(index == 0 ? 1 : 0)).setSelected(false);

		switch (index)
		{
			// Explorer Tab
			case 0 :

				pane.getChildren().get(0).setDisable(false);
				pane.getChildren().get(0).setVisible(true);
				pane.getChildren().get(1).setDisable(true);
				pane.getChildren().get(1).setVisible(false);

				break;

			// Debug Tab
			case 1 :

				pane.getChildren().get(1).setDisable(false);
				pane.getChildren().get(1).setVisible(true);
				pane.getChildren().get(0).setDisable(true);
				pane.getChildren().get(0).setVisible(false);

				break;
		}
	}
}