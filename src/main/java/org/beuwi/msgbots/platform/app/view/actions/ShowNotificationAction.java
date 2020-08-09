package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.openapi.Notification;
import org.beuwi.msgbots.platform.app.impl.Action;

// 알림 타이틀 변경하러면 추후 패키징할때 프로그램명 지정해야됨.
public class ShowNotificationAction implements Action
{
	private static Notification tray;

	@Override
	public void init()
	{
		tray = new Notification();
	}

	public static void execute(String title, String content)
	{
		tray.display(title, content);
	}

	@Override
	public String getName()
	{
		return "show.notification.action";
	}
}