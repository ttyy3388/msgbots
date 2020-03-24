package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.OpenDebugRoomAction;
import org.beuwi.simulator.platform.application.actions.ShowExplorerOptionAction;

public class SideBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpSideBar;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SideBarPart.class.getResource("/forms/SideBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpSideBar = (AnchorPane) nameSpace.get("anpSideBar");

		ExplorerPart.initialize();
		DebugPart.initialize();
	}

	private static class ExplorerPart
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

	private static class DebugPart
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
		return anpSideBar;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}