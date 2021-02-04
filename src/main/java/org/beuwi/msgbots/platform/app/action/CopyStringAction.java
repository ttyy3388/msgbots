package org.beuwi.msgbots.platform.app.action;

import javafx.application.Platform;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.beuwi.msgbots.platform.app.impl.Action;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class CopyStringAction implements Action {
    public static final Clipboard clipboard = Clipboard.getSystemClipboard();

    public static void execute(Object object) {
        execute(object.toString());
    }

    // 웹뷰에서 클립보드에 접근하는거는 JavaFX 쓰레드에서 실행해야 함 : 보안 때문인듯함
    public static void execute(String text) {
        Platform.runLater(() -> {
            ClipboardContent content = new ClipboardContent();
            content.putString(text);
            clipboard.setContent(content);
        });
    }

    // or
    /* public static void execute(String text) {
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(String.valueOf(text));
        clipboard.setContents(selection, selection);
    } */

    @Override
    public String getName() {
        return "copy.string.action";
    }
}
