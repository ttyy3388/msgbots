package org.beuwi.msgbots.actions;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.openapi.WinMessage;

// 알림 타이틀 변경하러면 추후 패키징할때 프로그램명 지정해야 됨
public class ShowWinMsgAction implements Executor {
	private static ShowWinMsgAction instance = null;

	private final WinMessage tray = new WinMessage();

	public void execute(String title, String content) {
		tray.display(title, content);
	}

	public static ShowWinMsgAction getInstance() {
		if (instance == null) {
			instance = new ShowWinMsgAction();
		}
		return instance;
	}
}