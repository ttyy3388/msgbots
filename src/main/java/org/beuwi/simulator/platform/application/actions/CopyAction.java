package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.List;

public class CopyAction
{
	public static final Clipboard CLIPBOARD = Clipboard.getSystemClipboard();

	// String
	public static void update(String data)
	{
		ClipboardContent content = new ClipboardContent();
		content.putString(data);
		CLIPBOARD.setContent(content);
	}

	public static void update(List list)
	{
		update(String.join("\n", list));
	}

	// File
	/* public static void update(File file)
	{

	}

	public static void update(List<String> data)
	{
		// List<String> list = listView.getSelectionModel().getSelectedItems();
	} */
}
