package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;

// Show Toast Message Action
public class AddToastMessageAction implements Action
{
	// private static ToastView listView;

	@Override
	public void init()
	{
		// listView = ToastAreaPart.getComponent();
	}

	public static void execute(/* Toast toast */)
	{
		// listView.addItem(toast);
	}

	@Override
	public String getName()
	{
		return "add.toast.message.action";
	}
}