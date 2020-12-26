package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.navis.DebugRoomNavi;
import org.beuwi.msgbots.platform.gui.control.ChatView;

public class SendChatMessageAction implements Action {
	private static ChatView listView;

	@Override
	public void init() {
		listView = DebugRoomNavi.getComponent("lsvChatView");
	}

	public static void execute(String message) {
		execute(message, false);
	}

	public static void execute(String message, boolean isbot) {
		ExecuteEditMenuAction.execute(message);
		/* listView.getItems().add(new Chat(message, isbot));

		if (!isbot) {
			ScriptManager.run(message);
		} */
	}

	@Override
	public String getName() {
		return "add.chat.message.action";
	}
}