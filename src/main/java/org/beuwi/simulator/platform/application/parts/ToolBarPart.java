package org.beuwi.simulator.platform.application.parts;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ToolBarPart
{
	private static AnchorPane anpToolBar;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/ToolBarForm.fxml"));
		loader.setController(null);
		loader.load();

		anpToolBar = (AnchorPane) loader.getNamespace().get("anpToolBar");
	}

	public static AnchorPane getRoot()
	{
		return anpToolBar;
	}

	/* public static AnchorPane getComponent()
	{
		return anpToolBar;
	} */
}