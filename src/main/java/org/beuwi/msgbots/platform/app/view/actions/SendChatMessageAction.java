package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;
import org.beuwi.msgbots.platform.gui.control.ChatItem;
import org.beuwi.msgbots.platform.gui.control.ChatView;

public class SendChatMessageAction implements Action {
	private static ChatView listView;

	@Override
	public void init() {
		listView = DebugRoomTab.getComponent("lsvChatView");
	}

	public static void execute(String message) {
		execute(message, false);
	}

	public static void execute(String message, boolean isBot) {
		listView.getItems().add(new ChatItem(message, isBot));

		// 봇이 전송한게 아니면 스크립트 실행
		if (!isBot) {
			ScriptManager.run(message);
		}
	}

	@Override
	public String getName() {
		return "send.chat.message.action";
	}
}