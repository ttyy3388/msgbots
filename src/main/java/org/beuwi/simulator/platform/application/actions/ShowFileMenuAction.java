package org.beuwi.simulator.platform.application.actions;

import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseEvent;
import org.beuwi.simulator.platform.application.parts.ToolBarPart;
import org.beuwi.simulator.platform.ui.components.IMenuItem;

public class ShowFileMenuAction
{
	final static IMenuItem NEW_SCRIPT_ITEM		 = new IMenuItem("New Script", "Ctrl + N");
	final static IMenuItem RELOAD_DISK_ITEM      = new IMenuItem("Reload All from Disk", "Ctrl + Alt + Y");
	final static IMenuItem SAVE_ITEM			 = new IMenuItem("Save", "Ctrl + S");
	final static IMenuItem SAVE_ALL_ITEM         = new IMenuItem("Save All", "Ctrl + Shift + S");
	final static IMenuItem IMPORT_SCRIPT_ITEM    = new IMenuItem("Import Script", "Ctrl + I");
	final static IMenuItem SETTINGS_ITEM         = new IMenuItem("Settings", "Ctrl + Alt + S");

	private static ContextMenu menu;
	private static Button button;

	public static void initialize()
	{
		button = (Button) ToolBarPart.getNameSpace().get("btnFileMenu");

		menu = new ContextMenu();
		menu.getItems().addAll
		(
			NEW_SCRIPT_ITEM,
			IMPORT_SCRIPT_ITEM,
			new SeparatorMenuItem(),
			RELOAD_DISK_ITEM,
			new SeparatorMenuItem(),
			SAVE_ITEM,
			SAVE_ALL_ITEM,
			new SeparatorMenuItem(),
			SETTINGS_ITEM
		);
	}

	public static void update(MouseEvent event)
	{
		menu.show(button, Side.BOTTOM, 0, 0);

		menu.showingProperty().addListener((observable, oldValue, newValue) ->
		{
			button.pseudoClassStateChanged(PseudoClass.getPseudoClass("showing"), newValue);
		});
	}
}