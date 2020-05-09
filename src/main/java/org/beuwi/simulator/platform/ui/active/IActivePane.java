package org.beuwi.simulator.platform.ui.active;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class IActivePane extends AnchorPane
{
	HBox area = new HBox();

	// Activity Bar
	VBox activity = new VBox();

	// Explorer Tab
	ToggleButton bots = new ToggleButton();
	// Simulation Tab
	ToggleButton debug = new ToggleButton();

	// Resize Bar
	StackPane resize = new StackPane();

	public IActivePane()
	{
	    AnchorPane.setTopAnchor(area, .0);
        AnchorPane.setRightAnchor(area, .0);
        AnchorPane.setBottomAnchor(area, .0);
        AnchorPane.setLeftAnchor(area, .0);

		AnchorPane.setTopAnchor(resize, .0);
		AnchorPane.setRightAnchor(resize, .0);
		AnchorPane.setBottomAnchor(resize, .0);

		// bots.getStyleClass().add("")
		bots.setPrefSize(30, 35);
		debug.setPrefSize(30, 35);

		activity.setPrefWidth(30);
		activity.getChildren().addAll(bots, debug);

        resize.setPrefWidth(7);

        getChildren().addAll(area, resize);
	}
}
