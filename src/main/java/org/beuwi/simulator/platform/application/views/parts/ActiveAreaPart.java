package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.actions.*;

public class ActiveAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane root;
	private static StackPane pane;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ActiveAreaPart.class.getResource("/forms/ActiveAreaPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		pane = (StackPane) nameSpace.get("stpResizeBar");

		pane.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.update(event);
		});

		ExplorerTabPart.initialize();
		DebugTabPart.initialize();
	}

	private static class ExplorerTabPart
	{
		private static Tab    tab;

		private static Button button;

		public static void initialize()
		{
			tab = (Tab) nameSpace.get("tabExplorerPart");
			button = (Button) nameSpace.get("btnShowOption");

			tab.selectedProperty().addListener((observable, oldValue, newValue) ->
			{
				if (newValue)
				{
					SelectActivityTabAction.update(0);
				}
			});

			button.setOnMousePressed(event ->
			{
				ShowExplorerOptionAction.update(event);
			});
		}
	}

	private static class DebugTabPart
	{
		private static Tab    tab;

		private static Button btnOpenChatRoom;
		private static Button btnShowGlobalLog;
		private static Button btnReloadAllBots;

		public static void initialize()
		{
			tab = (Tab) nameSpace.get("tabDebugPart");

			btnOpenChatRoom  = (Button) nameSpace.get("btnOpenChatRoom");
			btnShowGlobalLog = (Button) nameSpace.get("btnShowGlobalLog");
			btnReloadAllBots = (Button) nameSpace.get("btnReloadAllBots");

			tab.selectedProperty().addListener((observable, oldValue, newValue) ->
			{
				if (newValue)
				{
					SelectActivityTabAction.update(1);
				}
			});

			btnOpenChatRoom.setOnAction(event ->
			{
				OpenChatRoomTabAction.update();
			});

			btnShowGlobalLog.setOnAction(event ->
			{
				OpenGlobalLogTabAction.update();
			});

			btnReloadAllBots.setOnAction(event ->
			{
				// ReloadAllBotsAction.update();
			});
		}
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	// Children get(0) : Component
	public static TabPane getComponent()
	{
		return (TabPane) root.getChildren().get(0);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}