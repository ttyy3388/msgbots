package org.beuwi.simulator.platform.application.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import org.beuwi.simulator.platform.application.controllers.MenuBarController;

public class MenuBarView extends AnchorPane
{
	public MenuBarView() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/MenuView.fxml"));
		loader.setController(new MenuBarController());

		Region root = loader.load();

		AnchorPane.setTopAnchor   (root, .0);
		AnchorPane.setRightAnchor (root, .0);
		AnchorPane.setBottomAnchor(root, .0);
		AnchorPane.setLeftAnchor  (root, .0);

		getChildren().add(root);
		getStylesheets().add("/styles/MenuView.css");
	}
}