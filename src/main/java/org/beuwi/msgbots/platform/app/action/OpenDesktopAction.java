package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenDesktopAction implements Action {
	public static void execute(String path) {
		execute(new File(path));
	}

	public static void execute(File file) {
		try {
			Desktop.getDesktop().open(file);
		}
		catch (IOException e) {
			DisplayErrorDialogAction.execute(e);
		}
	}

	@Override
	public String getName() {
		return "open.desktop.action";
	}
}
