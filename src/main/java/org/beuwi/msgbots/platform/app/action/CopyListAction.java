package org.beuwi.msgbots.platform.app.action;

import javafx.scene.input.Clipboard;

import org.beuwi.msgbots.platform.app.impl.Action;

import java.util.List;

public class CopyListAction implements Action {
	public static final Clipboard clipboard = Clipboard.getSystemClipboard();

	public static void execute(List<String> list, String separator) {
        CopyStringAction.execute(String.join(separator, list));
	}

	@Override
	public String getName() {
		return "copy.list.action";
	}
}
