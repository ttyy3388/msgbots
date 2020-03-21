package org.beuwi.simulator.platform.application.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class StatusBarPart
{
	private static ObservableMap<String, Object> nameSpace;
	private static AnchorPane anchorPane;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/StatusBarForm.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();
		anchorPane = (AnchorPane) nameSpace.get("anpStatusBar");
	}

	public static AnchorPane getRoot()
	{
		return anchorPane;
	}

	/* public static AnchorPane getComponent()
	{
		return anpStatusBar;
	} */

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}