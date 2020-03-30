package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.OpenDebugRoomAction;
import org.beuwi.simulator.platform.application.actions.ShowExplorerOptionAction;
import org.beuwi.simulator.platform.application.actions.ShowInExplorerAction;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

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
		final static IMenuItem NEW_BOT_ITEM =  new IMenuItem("New Bot");
		final static IMenuItem SHOW_IN_EXPLORER_ITEM = new IMenuItem("Show in Explorer");

		private static ContextMenu menu;

		private static ListView lsvExplorerPart;
		private static Button btnShowOption;

		public static void initialize()
		{
			lsvExplorerPart = (ListView) nameSpace.get("lsvExplorerPart");
			btnShowOption = (Button) nameSpace.get("btnShowExplorerOption");

			menu = new ContextMenu();

			menu.getItems().addAll
			(
				NEW_BOT_ITEM,
				new SeparatorMenuItem(),
				SHOW_IN_EXPLORER_ITEM
			);

			SHOW_IN_EXPLORER_ITEM.setOnAction(event ->
			{
				ShowInExplorerAction.update();
			});

			lsvExplorerPart.setOnMousePressed(event ->
			{
				if (event.isSecondaryButtonDown())
				{
					menu.show(lsvExplorerPart, event.getScreenX(), event.getScreenY());
				}
			});

			btnShowOption.setOnMousePressed(event ->
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