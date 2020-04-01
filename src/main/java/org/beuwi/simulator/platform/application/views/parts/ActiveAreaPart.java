package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
		private static ToggleButton tab;

		private static Button button;

		public static void initialize()
		{
			tab = (ToggleButton) nameSpace.get("tgnExplorerTab");
			button = (Button) nameSpace.get("btnShowOption");

			tab.setOnMousePressed(event ->
			{
				if (event.isPrimaryButtonDown())
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
		private static ToggleButton tab;

		private static Button btnOpenChatRoom;
		private static Button btnShowGlobalLog;
		private static Button btnReloadAllBots;

		public static void initialize()
		{
			tab = (ToggleButton) nameSpace.get("tgnDebugTab");

			btnOpenChatRoom  = (Button) nameSpace.get("btnOpenChatRoom");
			btnShowGlobalLog = (Button) nameSpace.get("btnShowGlobalLog");
			btnReloadAllBots = (Button) nameSpace.get("btnReloadAllBots");

			tab.setOnMousePressed(event ->
			{
				if (event.isPrimaryButtonDown())
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
	public static HBox getComponent()
	{
		return (HBox) root.getChildren().get(0);
	}

	public static VBox getActivityBar()
	{
		return (VBox) getComponent().getChildren().get(0);
	}

	public static AnchorPane getSideBar()
	{
		return (AnchorPane) getComponent().getChildren().get(1);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}