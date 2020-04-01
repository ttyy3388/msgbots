package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ToolBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/ToolBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		Button btnFileMenu  = (Button) nameSpace.get("btnFileMenu");
		Button btnViewMenu  = (Button) nameSpace.get("btnViewMenu");
		Button btnDebugMenu = (Button) nameSpace.get("btnDebugMenu");

		btnFileMenu.setOnMousePressed(event ->
		{

		});

		btnViewMenu.setOnMousePressed(event ->
		{

		});

		btnDebugMenu.setOnMousePressed(event ->
		{

		});
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}