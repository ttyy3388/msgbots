package org.beuwi.simulator.platform.application.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.controllers.ActivityBarController;

public class SideBarView extends AnchorPane
{
	public SideBarView() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/ActiveView.fxml"));
		loader.setController(new ActivityBarController());

		Region root = loader.load();

		setTopAnchor   (root, .0);
		setRightAnchor (root, .0);
		setBottomAnchor(root, .0);
		setLeftAnchor  (root, .0);

		getChildren().add(root);
		getStylesheets().add("/styles/ActiveView.css");
	}
}