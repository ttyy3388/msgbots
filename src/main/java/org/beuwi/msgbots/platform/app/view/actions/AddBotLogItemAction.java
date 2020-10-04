package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.gui.control.Log;
import org.beuwi.msgbots.platform.gui.control.LogView;

public class AddBotLogItemAction implements Action
{
	private static LogView logView;

	@Override
	public void init()
	{
		logView = DebugAreaPart.GlobalLogTab.getComponent();
	}

	// Global Log
	public static void execute(Log item)
	{
		logView.addItem(item);
	}

	// Script Log
	public static void execute(String name, Log item)
	{

	}

	@Override
	public String getName()
	{
		return "add.global.log.item.action";
	}
}
