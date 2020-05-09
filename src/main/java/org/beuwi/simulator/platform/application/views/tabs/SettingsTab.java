package org.beuwi.simulator.platform.application.views.tabs;

import javafx.collections.ObservableMap;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.ui.components.IButton;
import org.beuwi.simulator.platform.ui.components.ICodeArea;
import org.beuwi.simulator.platform.ui.window.IWindowStage;
import org.beuwi.simulator.settings.Settings;
import org.beuwi.simulator.utils.ResourceUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class SettingsTab
{
    private static ObservableMap<String, Object> nameSpace;

    private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("SettingsTab"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		IButton btnApply   = (IButton) nameSpace.get("btnApply");
		IButton btnRefresh = (IButton) nameSpace.get("btnRefresh");

		btnApply.setButtonType(IButton.ACTION);
		btnApply.setOnAction(event ->
		{
			ProgramTab.apply();
			DebugTab.apply();
			BotsTab.apply();
		});

		btnRefresh.setOnAction(event ->
		{
			ProgramTab.refresh();
			DebugTab.refresh();
			BotsTab.refresh();
		});

		ProgramTab.initialize();
		DebugTab.initialize();
		BotsTab.initialize();
	}

	public static class ProgramTab
	{
		static Settings.Public data = Settings.getPublicSetting("program");

		static CheckBox chkAutoCompile = (CheckBox) nameSpace.get("chkAutoCompile");
		static CheckBox chkAutoSave = (CheckBox) nameSpace.get("chkAutoSave");

		static ICodeArea cdaScriptDefault = (ICodeArea) nameSpace.get("cdaScriptDefault");
		static ICodeArea cdaScriptUnified = (ICodeArea) nameSpace.get("cdaScriptUnified");

		static File fileScriptDefault = FileManager.getDataFile("script_default.js");
		static File fileScriptUnified = FileManager.getDataFile("script_unified.js");

		public static void initialize()
		{
			refresh();
		}

		public static void apply()
		{
			HashMap<String, Object> map = new HashMap<>();

			map.put("autoCompile", chkAutoCompile.isSelected());
			map.put("autoSave", chkAutoSave.isSelected());

			data.putMap(map);

			FileManager.save(fileScriptDefault, cdaScriptDefault.getText());
			FileManager.save(fileScriptUnified, cdaScriptUnified.getText());
		}

		public static void refresh()
		{
			chkAutoCompile.setSelected(data.getBoolean("autoCompile"));
			chkAutoSave.setSelected(data.getBoolean("autoSave"));

			cdaScriptDefault.setText(FileManager.read(fileScriptDefault));
			cdaScriptUnified.setText(FileManager.read(fileScriptUnified));
		}
	}

	public static class DebugTab
	{
		static Settings.Public data = Settings.getPublicSetting("debug");

		static TextField txfHtmlTime = (TextField) nameSpace.get("txfHtmlTime");

		static TextField txfRoomName = (TextField) nameSpace.get("txfRoomName");
		static TextField txfSenderName = (TextField) nameSpace.get("txfSenderName");
		static TextField txfBotName = (TextField) nameSpace.get("txfBotName");
		static TextField txfPackageName = (TextField) nameSpace.get("txfPackageName");

		static CheckBox chkIsGroupChat = (CheckBox) nameSpace.get("chkIsGroupChat");

		static IButton btnSenderProfile = (IButton) nameSpace.get("btnSenderProfile");
		static IButton btnBotProfile = (IButton) nameSpace.get("btnBotProfile");

		static AtomicReference<Image> imgSenderProfile = new AtomicReference<>();
		static AtomicReference<Image> imgBotProfile    = new AtomicReference<>();

		public static void initialize()
		{
			btnSenderProfile.setButtonType(IButton.ACTION);
			btnSenderProfile.setOnAction(event ->
			{
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif"));
				fileChooser.setTitle("Change Profile");

				File file = fileChooser.showOpenDialog(IWindowStage.getPrimaryStage());

				if (file != null)
				{
					imgSenderProfile.set(new Image(file.toURI().toString()));
				}
			});

			btnBotProfile.setButtonType(IButton.ACTION);
			btnBotProfile.setOnAction(event ->
			{
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif"));
				fileChooser.setTitle("Change Profile");

				File file = fileChooser.showOpenDialog(IWindowStage.getPrimaryStage());

				if (file != null)
				{
					imgBotProfile.set(new Image(file.toURI().toString()));
				}
			});

			refresh();
		}

		public static void apply()
		{
			HashMap<String, Object> map = new HashMap<>();

			map.put("room", txfRoomName.getText());
			map.put("sender", txfSenderName.getText());
			map.put("bot", txfBotName.getText());
			map.put("package", txfPackageName.getText());
			map.put("isGroupChat", chkIsGroupChat.isSelected());

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
		}

		public static void refresh()
		{
			txfHtmlTime.setText(data.getString("htmlTimeOut"));
			txfRoomName.setText(data.getString("room"));
			txfSenderName.setText(data.getString("sender"));
			txfBotName.setText(data.getString("bot"));
			txfPackageName.setText(data.getString("package"));

			chkIsGroupChat.setSelected(data.getBoolean("isGroupChat"));

			imgSenderProfile.set(null);
			imgBotProfile.set(null);
		}
	}

	public static class BotsTab
	{
		public static void initialize()
		{
			// refresh();
		}

		public static void apply()
		{

		}

		public static void refresh()
		{

		}
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}

	public static TabPane getComponent()
    {
        return (TabPane) root.getChildren().get(0);
    }

	public static AnchorPane getRoot()
	{
		return root;
	}
}