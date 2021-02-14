package org.beuwi.msgbots.openapi;

import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;

import java.awt.AWTException;
import java.awt.SystemTray;

// Window Notification
public class WinMessage {
	private final SystemTray tray;
	private final TrayIcon icon;

	public WinMessage() {
		tray = SystemTray.getSystemTray();
		icon = new TrayIcon();

		try {
			tray.add(icon);
		}
		catch (AWTException e) {
			DisplayErrorDialogAction.execute(e);
		}
	}

    public void display(String title, String message) {
        icon.display(title, message);
    }
}