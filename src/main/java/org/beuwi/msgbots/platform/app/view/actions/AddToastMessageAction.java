package org.beuwi.msgbots.platform.app.view.actions;

import javafx.application.Platform;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.ToastAreaPart;
import org.beuwi.msgbots.platform.gui.control.Toast;
import org.beuwi.msgbots.platform.gui.control.ToastView;
import org.beuwi.msgbots.platform.gui.enums.ToastType;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AddToastMessageAction implements Action {
	private static ToastView control;

	@Override
	public void init() {
		control = ToastAreaPart.getComponent();
	}

	public static void execute(Throwable error) {
		StringWriter message = new StringWriter();
		error.printStackTrace(new PrintWriter(message));

		execute(ToastType.ERROR, error.toString(), message.toString());
	}

	// Default Type : Event
	/* public static void execute(String title, String content)
	{
		execute(NoticeType.EVENT, title, content);
	} */

	public static void execute(ToastType type, String title, String content) {
		execute(new Toast(type, title, content));
	}

	public static void execute(Toast toast) {
		Platform.runLater(() -> {
			control.getItems().add(toast);
		});
	}

	@Override
	public String getName() {
		return "add.toast.message.action";
	}
}
