package org.beuwi.simulator.platform.application.parts;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class StatusBarPart
{
	private static AnchorPane anpStatusBar;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/StatusBarForm.fxml"));
		loader.setController(null);
		loader.load();

		anpStatusBar = (AnchorPane) loader.getNamespace().get("anpStatusBar");
	}

	public static AnchorPane getRoot()
	{
		return anpStatusBar;
	}

	/* public static AnchorPane getComponent()
	{
		return anpStatusBar;
	} */
}