package org.beuwi.msgbots.actions;

import javafx.application.Platform;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import org.beuwi.msgbots.base.impl.Executor;

import java.io.File;
import java.util.Arrays;

public class CopyClipboardAction implements Executor {
	private static CopyClipboardAction instance;
	private final Clipboard clipboard = Clipboard.getSystemClipboard();

	/* public static void execute(Object object) {
		execute(object.toString());
	} */

	public void execute(File... files) {
		Platform.runLater(() -> {
			ClipboardContent content = new ClipboardContent();
			content.putFiles(Arrays.asList(files));
			clipboard.setContent(content);
		});
	}

	// 웹뷰에서 클립보드에 접근하는거는 JavaFX 쓰레드에서 실행해야 함 : 보안 때문인듯함
	public void execute(String text) {
		Platform.runLater(() -> {
			ClipboardContent content = new ClipboardContent();
			content.putString(text);
			clipboard.setContent(content);
		});
	}

	public static CopyClipboardAction getInstance() {
		if (instance == null) {
			instance = new CopyClipboardAction();
		}
		return instance;
	}
}
