package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class StatusBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpStatusBar;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/StatusBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpStatusBar = (AnchorPane) nameSpace.get("anpStatusBar");
	}

	public static AnchorPane getRoot()
	{
		return anpStatusBar;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}