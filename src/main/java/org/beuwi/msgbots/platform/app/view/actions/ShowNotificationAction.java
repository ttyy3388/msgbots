package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.openapi.Notification;
import org.beuwi.msgbots.platform.app.impl.Action;

// 알림 타이틀 변경하러면 추후 패키징할때 프로그램명 지정해야됨.
public class ShowNotificationAction implements Action
{
	private final Notification tray = new Notification();

	private final String title;
	private final String content;

	public ShowNotificationAction(String title, String content)
	{
		this.title = title;
		this.content = content;
	}

	@Override
	public void execute()
	{
		tray.display(title, content);
	}

	@Override
	public String getName()
	{
		return "Show Notification Action";
	}
}