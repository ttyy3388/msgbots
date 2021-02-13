package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.dialogs.ShowErrorDialog;
import org.beuwi.msgbots.platform.gui.control.ToastItem;
import org.beuwi.msgbots.platform.gui.enums.ToastType;

import java.io.PrintWriter;
import java.io.StringWriter;

// Toast Message : Notification
public class DisplayErrorToastAction implements Action {

	public static void execute(Throwable error) {
		StringWriter message = new StringWriter();
		error.printStackTrace(new PrintWriter(message));

		ShowToastMessageAction.execute(
			new ToastItem(
				ToastType.ERROR,
				error.toString(),
				message.toString()
			)
		);
	}

	@Override
	public String getName() {
		return "display.error.toast.action";
	}
}