package org.beuwi.msgbots.platform.app.action;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.beuwi.msgbots.platform.app.impl.Action;

public class CopyStringAction implements Action {
    public static final Clipboard clipboard = Clipboard.getSystemClipboard();

    public static void execute(Object object) {
        execute(object.toString());
    }

    public static void execute(String text) {
        ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    @Override
    public String getName() {
        return "copy.string.action";
    }
}
