package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.beuwi.simulator.compiler.engine.ScriptManager;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.MainView;
import org.beuwi.simulator.platform.application.views.actions.OpenDebugRoomTabAction;
import org.beuwi.simulator.platform.application.views.actions.OpenGlobalLogTabAction;
import org.beuwi.simulator.platform.application.views.actions.ResizeSideBarAction;
import org.beuwi.simulator.platform.application.views.actions.SelectActivityTabAction;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.ui.components.IButton;
import org.beuwi.simulator.platform.ui.components.ICheckBox;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IListView;
import org.beuwi.simulator.platform.ui.components.ITextField;
import org.beuwi.simulator.settings.Settings;
import org.beuwi.simulator.utils.ResourceUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class ActiveAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane root;

	private static Pane resize;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("ActiveAreaPart"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		resize = getResizeBar();

		resize.setOnMouseDragged(event ->
		{
			ResizeSideBarAction.update(event);
		});

		BotsTab.initialize();
		DebugTab.initialize();
	}

	public static class BotsTab
	{
		private static IListView component = null;
		private static IContextMenu menu = null;

		public static void initialize()
		{
			ToggleButton toggle = (ToggleButton) nameSpace.get("tgnBotsTab");

			toggle.setOnMouseClicked(event ->
			{
				if (MouseButton.PRIMARY.equals(event.getButton()))
				{
					SelectActivityTabAction.update(0);
				}
			});

			component = (IListView) nameSpace.get("listView");

			// component.setContextMenu();
		}

		public static IListView getComponent()
		{
			return component;
		}
	}

	public static class DebugTab
	{
		public static void initialize()
		{
			ToggleButton toggle = (ToggleButton) nameSpace.get("tgnDebugTab");

			toggle.setOnMouseClicked(event ->
			{
				if (MouseButton.PRIMARY.equals(event.getButton()))
				{
					SelectActivityTabAction.update(1);
				}
			});

			initButtonBar();
			initOptionPane();
		}

		private static void initButtonBar()
		{
			Button btnReloadAllBots = (Button) nameSpace.get("btnReloadAllBots");
			Button btnOpenChatRoom  = (Button) nameSpace.get("btnOpenChatRoom");
			Button btnShowGlobalLog = (Button) nameSpace.get("btnShowGlobalLog");

			btnReloadAllBots.setOnAction(event ->
			{
				ScriptManager.allInitialize(true);
			});

			btnOpenChatRoom.setOnAction(event ->
			{
				OpenDebugRoomTabAction.update();
			});

			btnShowGlobalLog.setOnAction(event ->
			{
				OpenGlobalLogTabAction.update();
			});
		}

		private static void initOptionPane()
		{
			ITextField txfRoomName    = (ITextField) nameSpace.get("txfRoomName");
			ITextField txfSenderName  = (ITextField) nameSpace.get("txfSenderName");
			ITextField txfBotName     = (ITextField) nameSpace.get("txfBotName");
			ITextField txfPackageName = (ITextField) nameSpace.get("txfPackageName");

			ICheckBox chkIsGroupChat   = (ICheckBox) nameSpace.get("chkIsGroupChat");
			ICheckBox chkSenderProfile = (ICheckBox) nameSpace.get("chkSenderProfile");
			ICheckBox chkBotProfile    = (ICheckBox) nameSpace.get("chkBotProfile");

			IButton btnSenderProfile = (IButton) nameSpace.get("btnSenderProfile");
			IButton btnBotProfile    = (IButton) nameSpace.get("btnBotProfile");

			IButton btnApply   = (IButton) nameSpace.get("btnApply");
			IButton btnRefresh = (IButton) nameSpace.get("btnRefresh");

			AtomicReference<Image> imgSenderProfile = new AtomicReference<>();
			AtomicReference<Image> imgBotProfile    = new AtomicReference<>();

			Settings.Public data = Settings.getPublicSetting("debug");

			btnApply.setType(IButton.ACTION);

			// Refresh
			{
				txfRoomName.setText(data.getString("room"));
				txfSenderName.setText(data.getString("sender"));
				txfBotName.setText(data.getString("bot"));
				txfPackageName.setText(data.getString("package"));

				chkIsGroupChat.setSelected(data.getBoolean("isGroupChat"));
				// chkSenderProfile.setSelected(data.getBoolean("visibleSenderProfile"));
				// chkBotProfile.setSelected(data.getBoolean("visibleBotProfile"));
			}

			btnSenderProfile.setOnAction(event ->
			{
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif"));
				fileChooser.setTitle("Change Profile");

				File file = fileChooser.showOpenDialog(MainView.getStage());

				if (file != null)
				{
					imgSenderProfile.set(new Image(file.toURI().toString()));
				}
			});

			btnBotProfile.setOnAction(event ->
			{
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif"));
				fileChooser.setTitle("Change Profile");

				File file = fileChooser.showOpenDialog(MainView.getStage());

				if (file != null)
				{
					imgBotProfile.set(new Image(file.toURI().toString()));
				}
			});

			btnApply.setOnAction(event ->
			{
				HashMap<String, Object> map = new HashMap<>();

				map.put("room", txfRoomName.getText());
				map.put("sender", txfSenderName.getText());
				map.put("bot", txfBotName.getText());
				map.put("package", txfPackageName.getText());
				map.put("isGroupChat", chkIsGroupChat.isSelected());
				// map.put("visibleSenderProfile", chkSenderProfile.isSelected());
				// map.put("visibleBotProfile", chkBotProfile.isSelected());

				try
				{
					if (imgSenderProfile.get() != null)
					{
						ImageIO.write(SwingFXUtils.fromFXImage(imgSenderProfile.get(), null), "png", FileManager.getDataFile("profile_sender"));
					}

					if (imgBotProfile.get() != null)
					{
						ImageIO.write(SwingFXUtils.fromFXImage(imgBotProfile.get(), null), "png", FileManager.getDataFile("profile_bot"));
					}

				}
				catch (Exception e)
				{
					new ShowErrorDialog(e).display();
				}

				data.putMap(map);
			});

			btnRefresh.setOnAction(event ->
			{
				txfRoomName.setText(data.getString("room"));
				txfSenderName.setText(data.getString("sender"));
				txfBotName.setText(data.getString("bot"));
				txfPackageName.setText(data.getString("package"));

				chkIsGroupChat.setSelected(data.getBoolean("isGroupChat"));
				// chkSenderProfile.setSelected(data.getBoolean("visibleSenderProfile"));
				// chkBotProfile.setSelected(data.getBoolean("visibleBotProfile"));

				imgSenderProfile.set(null);
				imgBotProfile.set(null);
			});
		}
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static HBox getComponent()
	{
		return (HBox) root.getChildren().get(0);
	}

	public static VBox getActivityBar()
	{
		return (VBox) getComponent().getChildren().get(0);
	}

	public static StackPane getSideBar()
	{
		return (StackPane) getComponent().getChildren().get(1);
	}

	public static Pane getResizeBar()
	{
		return (Pane) getRoot().getChildren().get(1);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}