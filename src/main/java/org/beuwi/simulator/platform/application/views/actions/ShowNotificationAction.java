package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;

import java.awt.*;

public class ShowNotificationAction
{
	public static void update(String title, String content)
	{
		try
		{
			TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("icon.png"), "Messenger Bot Simulator");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Messenger Bot Simulator");
			trayIcon.setActionCommand("aa");

			SystemTray.getSystemTray().add(trayIcon);

			trayIcon.displayMessage(title, content, TrayIcon.MessageType.INFO);
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();
		}
	}
}