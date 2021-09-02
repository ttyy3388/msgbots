package org.beuwi.msgbots.action;

import org.beuwi.msgbots.base.impl.Executor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenDesktopAction implements Executor {
	private static OpenDesktopAction instance = null;

	private final Desktop desktop = Desktop.getDesktop();

	public void execute(String path) {
		execute(new File(path));
	}

	public void execute(File file) {
		try {
			desktop.open(file);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static OpenDesktopAction getInstance() {
		if (instance == null) {
			instance = new OpenDesktopAction();
		}
		return instance;
	}
}
