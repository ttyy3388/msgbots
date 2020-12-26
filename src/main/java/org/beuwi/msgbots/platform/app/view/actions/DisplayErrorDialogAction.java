package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.dialogs.ShowErrorDialog;

public class DisplayErrorDialogAction implements Action {

	public static void execute(Throwable t) {
		OpenDialogBoxAction.execute(new ShowErrorDialog(t));
	}

	@Override
	public String getName() {
		return "display.error.dialog.action";
	}
}