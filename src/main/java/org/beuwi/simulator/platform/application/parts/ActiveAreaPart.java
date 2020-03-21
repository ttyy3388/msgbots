package org.beuwi.simulator.platform.application.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.ShowExplorerOptionAction;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;

public class ActiveAreaPart
{
	private static ObservableMap<String, Object> nameSpace;
	private static AnchorPane anchorPane;
	private static TabPane tabPane;

	private static Button button;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActiveAreaPart.class.getResource("/forms/ActiveAreaForm.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anchorPane = (AnchorPane) nameSpace.get("anpActiveArea");
		tabPane = (TabPane) nameSpace.get("tapActiveArea");
		button = (Button) nameSpace.get("btnExplorerOption");

		anchorPane.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.update(event);
		});

		button.setOnMousePressed(event ->
		{
			ShowExplorerOptionAction.update(event);
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