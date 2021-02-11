package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenBrowserAction implements Action {
	@Override
	public void init() {

	}

	public static void execute(String url) {
		try {
			execute(new URI(url));
		}
		catch (URISyntaxException e) {
			DisplayErrorDialogAction.execute(e);
		}
	}

	public static void execute(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		}
		catch (IOException e) {
			DisplayErrorDialogAction.execute(e);
		}
	}

	@Override
    public String getName() {
        return "open.brose.action";
    }
}