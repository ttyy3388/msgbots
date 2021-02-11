package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.openapi.WinMessage;
import org.beuwi.msgbots.platform.app.impl.Action;

// If want to change title after when while packing set program name
// 알림 타이틀 변경하러면 추후 패키징할때 프로그램명 지정해야됨.
public class ShowWinMsgAction implements Action {
	private static WinMessage tray = new WinMessage();

	public static void execute(String title, String content) {
		tray.display(title, content);
	}

	@Override
	public String getName() {
		return "show.win.notice.action";
	}
}