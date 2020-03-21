package org.beuwi.simulator.platform.application.parts;

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
	private static AnchorPane anchorPane;

	private static Button btnFileMenu;
	private static Button btnViewMenu;
	private static Button btnDebugMenu;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/ToolBarForm.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anchorPane = (AnchorPane) nameSpace.get("anpToolBar");
		btnFileMenu = (Button) nameSpace.get("btnFileMenu");
		btnViewMenu = (Button) nameSpace.get("btnViewMenu");
		btnDebugMenu = (Button) nameSpace.get("btnDebugMenu");

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