package org.beuwi.msgbots.action;

import org.beuwi.msgbots.base.impl.Executor;

import java.awt.Desktop;
import java.net.URI;

public class OpenBrowserAction implements Executor {
	private static OpenBrowserAction instance = null;

	private final Desktop desktop = Desktop.getDesktop();

	public void execute(String url) {
		try {
			execute(new URI(url));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void execute(URI uri) {
		try {
			desktop.browse(uri);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static OpenBrowserAction getInstance() {
		if (instance == null) {
			instance = new OpenBrowserAction();
		}
		return instance;
	}
}