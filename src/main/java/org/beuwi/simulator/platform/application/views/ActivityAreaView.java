package org.beuwi.simulator.platform.application.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.controllers.ActivityAreaController;

public class ActivityAreaView extends AnchorPane
{
	public ActivityAreaView() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/ActivityAreaForm.fxml"));
		loader.setController(new ActivityAreaController());

		Region root = loader.load();

		setTopAnchor   (root, .0);
		setRightAnchor (root, .0);
		setBottomAnchor(root, .0);
		setLeftAnchor  (root, .0);

		getChildren().add(root);
	}
}