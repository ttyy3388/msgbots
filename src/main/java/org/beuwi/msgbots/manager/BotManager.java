package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;

public class BotManager
{
	public static void setPower(String name, boolean power)
	{
		BotListTab.getComponent().getItem(name).setPower(power);
	}

	public static void setCompiled(String name, boolean compiled)
	{
		BotListTab.getComponent().getItem(name).setCompiled(compiled);
	}

	public static boolean getPower(String name)
	{
		return BotListTab.getComponent().getItem(name).getPower();
	}

	public static boolean isCompiled(String name)
	{
		return BotListTab.getComponent().getItem(name).isCompiled();
	}
}