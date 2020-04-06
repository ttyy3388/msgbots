package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.OpenDebugRoomTabAction;
import org.beuwi.simulator.platform.application.actions.OpenGlobalLogTabAction;
import org.beuwi.simulator.platform.application.actions.SaveAllEditorTabsAction;
import org.beuwi.simulator.platform.application.actions.SaveEditorTabAction;
import org.beuwi.simulator.platform.application.views.dialogs.CreateBotDialog;
import org.beuwi.simulator.platform.application.views.dialogs.ImportScriptDialog;
import org.beuwi.simulator.platform.application.views.dialogs.SettingsDialog;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

public class ToolBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/ToolBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		FileMenu.initialize();
		ViewMenu.initialize();
		DebugMenu.initialize();
	}

	private static class FileMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu
			(
				new IMenuItem("New Bot", "Ctrl + N", event -> new CreateBotDialog().display()),
				new IMenuItem("Import Script", "Ctrl + I", event -> new ImportScriptDialog().display()),
				new SeparatorMenuItem(),
				new IMenuItem("Save", "Ctrl + S", event -> SaveEditorTabAction.update()),
				new IMenuItem("Save All", "Ctrl + Shift + S", event -> SaveAllEditorTabsAction.update()),
				new SeparatorMenuItem(),
				new IMenuItem("Settings", "Ctrl + Alt + S", event -> new SettingsDialog().display())
			);

			Button button = (Button) nameSpace.get("btnFileMenu");
			button.setOnMousePressed(event ->
			{
				if (event.isPrimaryButtonDown())
				{
					menu.show(button, Side.BOTTOM);
				}
			});

			menu.showingProperty().addListener((observable, oldValue, newValue) ->
			{
				button.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
			});
		}
	}

	private static class ViewMenu
	{
		public static void initialize()
		{
			IContextMenu menu = new IContextMenu();

			Button button = (Button) nameSpace.get("btnViewMenu");
			button.setOnMousePressed(event ->
			{
				if (event.isPrimaryButtonDown())
				{
					menu.show(button, Side.BOTTOM);
				}
			});

			menu.showingProperty().addListener((observable, oldValue, newValue) ->
			{
				button.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
			});
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

			Button button = (Button) nameSpace.get("btnDebugMenu");
			button.setOnMousePressed(event ->
			{
				if (event.isPrimaryButtonDown())
				{
					menu.show(button, Side.BOTTOM);
				}
			});

			menu.showingProperty().addListener((observable, oldValue, newValue) ->
			{
				button.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
			});
		}
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}