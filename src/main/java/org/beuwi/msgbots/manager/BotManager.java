package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;

public class BotManager
{
	public static void setPower(String name, boolean power)
	{
		BotListTab.getComponent().getBot(name).setPower(power);
	}

	public static void setCompiled(String name, boolean compiled)
	{
		BotListTab.getComponent().getBot(name).setCompiled(compiled);
	}

	public static boolean getPower(String name)
	{
		return BotListTab.getComponent().getBot(name).getPower();
	}

	public static boolean isCompiled(String name)
	{
		return BotListTab.getComponent().getBot(name).isCompiled();
	}
}