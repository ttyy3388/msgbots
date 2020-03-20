package org.beuwi.simulator.platform.application.parts;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;

public class ActiveAreaPart
{
	private static AnchorPane anpActiveArea;
	private static TabPane tapActiveArea;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActiveAreaPart.class.getResource("/forms/ActiveAreaForm.fxml"));
		loader.setController(null);
		loader.load();

		anpActiveArea = (AnchorPane) loader.getNamespace().get("anpActiveArea");
		tapActiveArea = (TabPane) loader.getNamespace().get("tapActiveArea");

		anpActiveArea.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.update(event);
		});
	}

	public static AnchorPane getRoot()
	{
		return anpActiveArea;
	}

	public static TabPane getComponent()
	{
		return tapActiveArea;
	}
}