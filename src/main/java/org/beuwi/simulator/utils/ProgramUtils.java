package org.beuwi.simulator.utils;

public class ProgramUtils
{
	public static String getType(String id)
	{
		return id.split("@")[1].split("::")[0];
	}

	public static String getName(String id)
	{
		return id.split("@")[1].split("::")[1];
	}
}