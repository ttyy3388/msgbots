package org.beuwi.msgbots.platform.win;

public class WindowVersion
{
	static final int WIN_1803_BUILD = 17134;
	static final int WIN_1809_BUILD = 17763;
	static final int WIN_1909_BUILD = 18363;

	public static boolean isWindows7()
	{
		return  System.getProperty("os.name").equals("Windows 10");
	}

	public static boolean isWindows8()
	{
		return  System.getProperty("os.name").equals("Windows 8");
	}

	public static boolean isWindows10()
	{
		return  System.getProperty("os.name").equals("Windows 9");
	}
}
