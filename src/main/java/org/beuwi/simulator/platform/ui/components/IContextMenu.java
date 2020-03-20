package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.SeparatorMenuItem;

public class IContextMenu extends ContextMenu
{
	final IMenuItem CUT_ITEM		   		= new IMenuItem("Cut", "Ctrl + X");
	final IMenuItem COPY_ITEM 			    = new IMenuItem("Copy", "Ctrl + C");
	final IMenuItem CLOSE_ITEM 			    = new IMenuItem("Close", "Ctrl + W");
	final IMenuItem PASTE_ITEM      	    = new IMenuItem("Paste", "Ctrl + V");
	final IMenuItem RENAME_ITEM 		    = new IMenuItem("Rename");
	final IMenuItem DELETE_ITEM			    = new IMenuItem("Delete");
	final IMenuItem REFRESH_ITEM            = new IMenuItem("Refresh");
	final IMenuItem SETTINGS_ITEM           = new IMenuItem("Settings", "Ctrl + Alt + S");
	final IMenuItem SAVE_ALL_ITEM           = new IMenuItem("Save All", "Ctrl + Shift + S");
	final IMenuItem COPY_PATH_ITEM		    = new IMenuItem("Copy Path");
	final IMenuItem CLOSE_ALL_ITEM 	   	    = new IMenuItem("Close All");
	final IMenuItem NEW_SCRIPT_ITEM		    = new IMenuItem("New Script", "Ctrl + N");
	final IMenuItem ALL_SELECT_ITEM 		= new IMenuItem("All Select", "Ctrl + A");
	final IMenuItem RELOAD_DISK_ITEM        = new IMenuItem("Reload All from Disk", "Ctrl + Alt + Y");
	final IMenuItem CLOSE_OTHERS_ITEM 	    = new IMenuItem("Close Others");
	final IMenuItem IMPORT_SCRIPT_ITEM      = new IMenuItem("Import Script", "Ctrl + I");
	final IMenuItem SHOW_IN_EXPLORER_ITEM   = new IMenuItem("Show In Explorer");
	final IMenuItem COPY_RELATIVE_PATH_ITEM = new IMenuItem("Copy Relative Path");

	public IContextMenu(int type)
	{
		switch (type)
		{
			case IContextMenuType.WINDOW :
				getItems().addAll
				(
					NEW_SCRIPT_ITEM,
					IMPORT_SCRIPT_ITEM,
					new SeparatorMenuItem(),
					SETTINGS_ITEM,
					new SeparatorMenuItem(),
					RELOAD_DISK_ITEM,
					new SeparatorMenuItem(),
					SAVE_ALL_ITEM
				);
				break;
			case IContextMenuType.EDITOR :
				getItems().addAll
				(
					CUT_ITEM,
					COPY_ITEM,
					PASTE_ITEM
				);
				break;
			case IContextMenuType.EXPLORER :
				getItems().addAll
				(
					NEW_SCRIPT_ITEM,
					new SeparatorMenuItem(),
					SHOW_IN_EXPLORER_ITEM,
					new SeparatorMenuItem(),
					COPY_PATH_ITEM,
					COPY_RELATIVE_PATH_ITEM
				);
				break;
		}

		setAutoHide(true);
	}
}