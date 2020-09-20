package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;

public class BotManager
{
	public static void setPower(String name, boolean power)
	{
		SideAreaPart.BotListTab.getComponent().getBot(name).setPower(power);
	}

	public static void setCompiled(String name, boolean compiled)
	{
		SideAreaPart.BotListTab.getComponent().getBot(name).setCompiled(compiled);
	}

	public static boolean getPower(String name)
	{
		return SideAreaPart.BotListTab.getComponent().getBot(name).getPower();
	}

	public static boolean isCompiled(String name)
	{
		return SideAreaPart.BotListTab.getComponent().getBot(name).isCompiled();
	}
}