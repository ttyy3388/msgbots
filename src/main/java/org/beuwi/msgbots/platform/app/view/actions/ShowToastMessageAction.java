package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.ToastAreaPart;
import org.beuwi.msgbots.platform.gui.control.Toast;
import org.beuwi.msgbots.platform.gui.control.ToastView;

public class ShowToastMessageAction implements Action
{
	private static ToastView listView;

	@Override
	public void init()
	{
		listView = ToastAreaPart.getComponent();
	}

	public static void execute(String message)
	{
		listView.getItems().add(new Toast(message));
	}

	@Override
	public String getName()
	{
		return "show.toast.message.action";
	}
}