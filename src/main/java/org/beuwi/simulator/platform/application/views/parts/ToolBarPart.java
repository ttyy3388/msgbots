package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SeparatorMenuItem;
import org.beuwi.simulator.platform.application.views.actions.*;
import org.beuwi.simulator.platform.application.views.dialogs.CreateBotDialogBox;
import org.beuwi.simulator.platform.application.views.dialogs.ImportScriptDialogBox;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

public class ToolBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static Node root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/ToolBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		FileMenu.initialize();
		EditMenu.initialize();
		ViewMenu.initialize();
		DebugMenu.initialize();
	}

	private static class FileMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu
			(
				new IMenuItem("New Bot", "Ctrl + N", event -> new CreateBotDialogBox().display()),
				new IMenuItem("Import Script", "Ctrl + I", event -> new ImportScriptDialogBox().display()),
				new SeparatorMenuItem(),
				new IMenuItem("Save", "Ctrl + S", event -> SaveEditorTabAction.update()),
				new IMenuItem("Save All", "Ctrl + Shift + S", event -> SaveAllEditorTabsAction.update()),
				new SeparatorMenuItem(),
				new IMenuItem("Reload All From Disk", "Ctrl + Alt + Y", event -> RefreshExplorerAction.update()),
				new SeparatorMenuItem(),
				new IMenuItem("Settings", "Ctrl + Alt + S", event -> OpenSettingsTabAction.update())
			);

			menu.setNode((Button) nameSpace.get("btnFileMenu"));
		}
	}

	private static class EditMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu
			(
				new IMenuItem("Undo", "Ctrl + Z"),
				new IMenuItem("Redo", "Ctrl + Y"),
				new SeparatorMenuItem(),
				new IMenuItem("Cut", "Ctrl + X"),
				new IMenuItem("Copy", "Ctrl + C"),
				new IMenuItem("Paste", "Ctrl + V")
			);

			menu.setNode((Button) nameSpace.get("btnEditMenu"));
		}
	}

	private static class ViewMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu();

			menu.setNode((Button) nameSpace.get("btnViewMenu"));
		}
	}

	private static class DebugMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu
			(
				new IMenuItem("Open Debug Room", event -> OpenDebugRoomTabAction.update()),
				new IMenuItem("Show Global Log", event -> OpenGlobalLogTabAction.update())
			);

			menu.setNode((Button) nameSpace.get("btnDebugMenu"));
		}
	}

	public static Node getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}