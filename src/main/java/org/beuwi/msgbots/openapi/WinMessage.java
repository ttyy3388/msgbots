package org.beuwi.msgbots.openapi;

import java.awt.*;

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
		catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void display(String title, String message) {
        icon.display(title, message);
    }
}