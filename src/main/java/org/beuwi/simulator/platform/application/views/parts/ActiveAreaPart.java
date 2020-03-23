package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;
import org.beuwi.simulator.platform.application.actions.ShowExplorerOptionAction;
import org.beuwi.simulator.platform.application.actions.OpenDebugRoomAction;

public class ActiveAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpActiveArea;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActiveAreaPart.class.getResource("/forms/ActiveAreaPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpActiveArea = (AnchorPane) nameSpace.get("anpActiveArea");
		// tapActiveArea = (TabPane) nameSpace.get("tapActiveArea");

		anpActiveArea.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.update(event);
		});

		ExplorerTab.initialize();
		SimulationTab.initialize();
	}

	public static class ExplorerTab
	{
		private static Button btnShowExplorerOption;

		public static void initialize()
		{
			btnShowExplorerOption = (Button) nameSpace.get("btnShowExplorerOption");

			btnShowExplorerOption.setOnMousePressed(event ->
			{
				ShowExplorerOptionAction.update(event);
			});
		}
	}

	public static class SimulationTab
	{
		private static Button btnOpenDebugRoom;
		private static Button btnShowAllLogs;
		private static Button btnReloadAllBots;

		public static void initialize()
		{
			btnOpenDebugRoom = (Button) nameSpace.get("btnOpenDebugRoom");
			btnShowAllLogs   = (Button) nameSpace.get("btnShowAllLogs");
			btnReloadAllBots = (Button) nameSpace.get("btnReloadAllBots");

			btnOpenDebugRoom.setOnAction(event ->
			{
				OpenDebugRoomAction.update();
			});

			btnShowAllLogs.setOnAction(event ->
			{
				// ShowAllLogsAction.update();
			});

			btnReloadAllBots.setOnAction(event ->
			{
				// ReloadAllBotsAction.update();
			});
		}
	}

	public static AnchorPane getRoot()
	{
		return anpActiveArea;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}