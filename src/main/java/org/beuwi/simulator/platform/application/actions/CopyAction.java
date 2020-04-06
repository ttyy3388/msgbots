package org.beuwi.simulator.platform.application.actions;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyAction
{
    final public static Clipboard CLIPBOARD = Clipboard.getSystemClipboard();

    // String
    public static void update(String data)
	{
        ClipboardContent content = new ClipboardContent();
        content.putString(data);
        CLIPBOARD.setContent(content);
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
