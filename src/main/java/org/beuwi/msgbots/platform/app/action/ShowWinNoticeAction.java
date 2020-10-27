package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.openapi.WinNotice;
import org.beuwi.msgbots.platform.app.impl.Action;

// 알림 타이틀 변경하러면 추후 패키징할때 프로그램명 지정해야됨.
public class ShowWinNoticeAction implements Action
{
	private static WinNotice tray = new WinNotice();

	@Override
	public void init()
	{

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