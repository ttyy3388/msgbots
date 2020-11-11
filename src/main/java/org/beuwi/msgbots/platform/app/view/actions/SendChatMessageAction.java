package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.compiler.engine.ScriptManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.DebugRoomTab;
import org.beuwi.msgbots.platform.gui.control.Chat;
import org.beuwi.msgbots.platform.gui.control.ChatView;

public class SendChatMessageAction implements Action
{
	private static ChatView listView;

	@Override
	public void init()
	{
		listView = DebugRoomTab.getComponent("lsvChatView");
	}

	public static void execute(String message)
	{
		execute(message, false);
	}

	public static void execute(String message, boolean isbot)
	{
		listView.addItem(new Chat(message, isbot));

		if (!isbot)
		{
			ScriptManager.run(message);
		}
	}

	@Override
	public String getName()
	{
		return "add.chat.message.action";
	}
}