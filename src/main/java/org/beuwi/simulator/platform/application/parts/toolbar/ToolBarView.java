package org.beuwi.simulator.platform.application.parts.toolbar;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class ToolBarView extends AnchorPane
{
	public ToolBarView() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/MenuBarForm.fxml"));
		loader.setController(new ToolBarController());

		Region root = loader.load();

		AnchorPane.setTopAnchor   (root, .0);
		AnchorPane.setRightAnchor (root, .0);
		AnchorPane.setBottomAnchor(root, .0);
		AnchorPane.setLeftAnchor  (root, .0);

		getChildren().add(root);
	}
}