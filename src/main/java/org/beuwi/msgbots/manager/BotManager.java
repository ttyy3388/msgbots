package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.platform.app.view.navis.BotListNavi;

public class BotManager
{
	public static void setPower(String name, boolean power)
	{
		BotListNavi.getComponent().getBot(name).setPower(power);
	}

	public static void setCompiled(String name, boolean compiled)
	{
		BotListNavi.getComponent().getBot(name).setCompiled(compiled);
	}

	public static boolean getPower(String name)
	{
		return BotListNavi.getComponent().getBot(name).getPower();
	}

	public static boolean isCompiled(String name)
	{
		return BotListNavi.getComponent().getBot(name).isCompiled();
	}
}