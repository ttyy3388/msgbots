package org.beuwi.simulator.platform.application.parts;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;

public class ActiveAreaPart
{
	private static ObservableMap<String, Object> nameSpace;
	private static AnchorPane anchorPane;
	private static TabPane tabPane;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActiveAreaPart.class.getResource("/forms/ActiveAreaForm.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anchorPane = (AnchorPane) nameSpace.get("anpActiveArea");
		tabPane = (TabPane) nameSpace.get("tapActiveArea");

		anchorPane.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.action(event);
		});
	}

	public static AnchorPane getRoot()
	{
		return anchorPane;
	}

	public static TabPane getComponent()
	{
		return tabPane;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}