package org.beuwi.simulator.platform.application.views.parts;

import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.beuwi.simulator.platform.application.actions.*;
import org.beuwi.simulator.settings.Settings;

import java.util.HashMap;

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
			tab.setOnMousePressed(event ->
			{
				if (event.isPrimaryButtonDown())
				{
					SelectActivityTabAction.update(0);
				}
			});

			button = (Button) nameSpace.get("btnShowOption");
			button.setOnMousePressed(event ->
			{
				ShowExplorerOptionAction.update(event);
			});
		}
	}

	private static class DebugTabPart
	{
		private static ToggleButton tab;

		public static void initialize()
		{
			tab = (ToggleButton) nameSpace.get("tgnDebugTab");
			tab.setOnMousePressed(event ->
			{
				if (event.isPrimaryButtonDown())
				{
					SelectActivityTabAction.update(1);
				}
			});

			initButtonBar();
			initOptionPane();
		}

		private static void initButtonBar()
		{
			Button btnOpenChatRoom  = (Button) nameSpace.get("btnOpenChatRoom");
			Button btnShowGlobalLog = (Button) nameSpace.get("btnShowGlobalLog");
			Button btnReloadAllBots = (Button) nameSpace.get("btnReloadAllBots");

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

		private static void initOptionPane()
		{
			// 추후 Chat Room이 켜져있어야지만 활성화 되도록 변경

			TextField txfRoomName    = (TextField) nameSpace.get("txfRoomName");
			TextField txfSenderName  = (TextField) nameSpace.get("txfSenderName");
			TextField txfBotName     = (TextField) nameSpace.get("txfBotName");
			TextField txfPackageName = (TextField) nameSpace.get("txfPackageName");

			JFXToggleButton tgnIsGroupChat   = (JFXToggleButton) nameSpace.get("tgnIsGroupChat");
			JFXToggleButton tgnSenderProfile = (JFXToggleButton) nameSpace.get("tgnSenderProfile");
			JFXToggleButton tgnBotProfile 	 = (JFXToggleButton) nameSpace.get("tgnBotProfile");

			Button btnSenderProfile = (Button) nameSpace.get("btnSenderProfile");
			Button btnBotProfile    = (Button) nameSpace.get("btnBotProfile");

			Button btnApply  = (Button) nameSpace.get("btnBotProfile");
			Button btnCancel = (Button) nameSpace.get("btnBotProfile");

			Settings.Public data = Settings.getPublicSetting("chat");

			txfRoomName.setText(data.getString("room"));
			txfSenderName.setText(data.getString("sender"));
			txfBotName.setText(data.getString("bot"));
			txfPackageName.setText(data.getString("package"));

			tgnIsGroupChat.setSelected(data.getBoolean("isGroupChat"));
			tgnSenderProfile.setSelected(data.getBoolean("visibleSenderProfile"));
			tgnBotProfile.setSelected(data.getBoolean("visibleSenderProfile"));

			btnSenderProfile.setOnAction(event -> {});
			btnBotProfile.setOnAction(event -> {});

			btnApply.setOnAction(event ->
			{
				HashMap<String, Object> map = new HashMap<>();

				map.put("room", txfRoomName.getText());
				map.put("sender", txfSenderName.getText());
				map.put("bot", txfBotName.getText());
				map.put("package", txfPackageName.getText());
				map.put("isGroupChat", tgnIsGroupChat.isSelected());
				map.put("visibleSenderProfile", tgnSenderProfile.isSelected());
				map.put("visibleSenderProfile", tgnBotProfile.isSelected());

				data.putMap(map);
			});

			btnCancel.setOnAction(event ->
			{

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