package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.ShowDebugMenuAction;
import org.beuwi.simulator.platform.application.actions.ShowFileMenuAction;
import org.beuwi.simulator.platform.application.actions.ShowViewMenuAction;

public class ToolBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpToolBar;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/ToolBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpToolBar = (AnchorPane) nameSpace.get("anpToolBar");

		Button btnFileMenu  = (Button) nameSpace.get("btnFileMenu");
		Button btnViewMenu  = (Button) nameSpace.get("btnViewMenu");
		Button btnDebugMenu = (Button) nameSpace.get("btnDebugMenu");

		btnFileMenu.setOnMousePressed(event ->
		{
			ShowFileMenuAction.update(event);
		});

		btnViewMenu.setOnMousePressed(event ->
		{
			ShowViewMenuAction.update(event);
		});

		btnDebugMenu.setOnMousePressed(event ->
		{
			ShowDebugMenuAction.update(event);
		});
	}

	public static AnchorPane getRoot()
	{
		return anpToolBar;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}