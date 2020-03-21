package org.beuwi.simulator.platform.application.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ToolBarPart
{
	private static ObservableMap<String, Object> nameSpace;
	private static AnchorPane anchorPane;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/ToolBarForm.fxml"));
		loader.setController(null);
		loader.load();

		anchorPane = (AnchorPane) loader.getNamespace().get("anpToolBar");
	}

	public static AnchorPane getRoot()
	{
		return anchorPane;
	}

	/* public static AnchorPane getComponent()
	{
		return anpToolBar;
	} */

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}