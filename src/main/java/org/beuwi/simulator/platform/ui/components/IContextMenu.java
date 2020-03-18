package org.beuwi.simulator.platform.ui.components;

import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;

public class IContextMenu extends ContextMenu
{
	final public MenuItem CUT_ITEM = getMenuItem("Cut", "Ctrl + X");
	final public MenuItem COPY_ITEM = getMenuItem("Copy", "Ctrl + C");
	final public MenuItem PASTE_ITEM = getMenuItem("Paste", "Ctrl + V");
	final public MenuItem SETTINGS_ITEM = getMenuItem("Settings", "Ctrl + Alt + S");
	final public MenuItem SAVE_ALL_ITEM  = getMenuItem("Save All", "Ctrl + Shift + S");
	final public MenuItem NEW_SCRIPT_ITEM = getMenuItem("New Script", "Ctrl + N");
	final public MenuItem IMPORT_SCRIPT_ITEM	= getMenuItem("Import Script", "Ctrl + I");
	final public MenuItem RELOAD_ALL_FROM_DISK_ITEM = getMenuItem("Reload All from Disk", "Ctrl + Alt + Y");

	public IContextMenu(int type)
	{
		switch (type)
		{
			case IContextMenuType.EDITOR :

				getItems().addAll
				(
					CUT_ITEM,
					COPY_ITEM,
					PASTE_ITEM
				);

				break;

			case IContextMenuType.EXPLORER :

				getItems().add
				(
					NEW_SCRIPT_ITEM
				);

				break;
		}
	}

	public MenuItem getMenuItem(String text, String command)
	{
		return getMenuItem(text, command, null);
	}

	public MenuItem getMenuItem(String text, String command, EventHandler<ActionEvent> handler)
	{
		MenuItem item = new MenuItem();

		Label name = new Label(text);

		name.setPrefWidth(150);

		if (!command.isEmpty())
		{
			item.setAccelerator(KeyCombination.keyCombination(command));
		}

		item.setGraphic(name);
		item.setOnAction(handler);

		return item;
	}
}