package org.beuwi.simulator.platform.application.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.controllers.StatusBarController;

public class StatusBarView extends AnchorPane
{
	public StatusBarView() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/StatusBarForm.fxml"));
		loader.setController(new StatusBarController());

		Region root = loader.load();

		AnchorPane.setTopAnchor   (root, .0);
		AnchorPane.setRightAnchor (root, .0);
		AnchorPane.setBottomAnchor(root, .0);
		AnchorPane.setLeftAnchor  (root, .0);

		getChildren().add(root);
	}
}