package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;

public class AddChatMessageAction implements Action
{
	private final String message;
	private final boolean isbot;

	public AddChatMessageAction(String message, boolean isbot)
	{
		this.message = message;
		this.isbot = isbot;
	}

	@Override
	public void execute()
	{
		/* if (message.length() >= 200)
		{
			// 전체보기
		}

		if (message.length() >= 20000)
		{
			return ;
		} */
	}

	@Override
	public String getName()
	{
		return "Add Chat Message Action";
	}
}