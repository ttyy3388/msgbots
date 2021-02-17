package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.dialogs.DisplayErrorDialog;

// Dialog Box
public class DisplayErrorDialogAction implements Action {

	public static void execute(Throwable throwable) {
		OpenDialogBoxAction.execute(new DisplayErrorDialog(throwable));
	}

	@Override
	public String getName() {
		return "display.error.dialog.action";
	}
}