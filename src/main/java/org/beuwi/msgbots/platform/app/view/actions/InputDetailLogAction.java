package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.ToolAreaPart;
import org.beuwi.msgbots.platform.app.view.tabs.DetailLogTab;
import org.beuwi.msgbots.platform.gui.control.TextArea;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InputDetailLogAction implements Action {
	private static final SimpleDateFormat formatter = new SimpleDateFormat ( "HH:mm:ss");
	private static TextArea textArea;

	@Override
	public void init() {
		textArea = DetailLogTab.getComponent();
	}

	public static void execute(String task, String text) {
		execute(formatter.format(new Date()), task, text);
	}

	public static void execute(String time, String task, String text) {
		textArea.appendText("[" + time + "] < Task : " + task + " > : " + text + "\n");
	}

	@Override
	public String getName() {
		return "input.detail.log.action";
	}
}