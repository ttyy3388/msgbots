package org.beuwi.simulator.managers;

import org.beuwi.simulator.platform.application.actions.AddDebugLogAction;
import org.beuwi.simulator.platform.ui.components.ILogType;

public class LogManager
{
	public static void addError(String msg)
	{
		AddDebugLogAction.update(msg, ILogType.ERROR);
	}

	public static void addWarning(String msg)
	{
		AddDebugLogAction.update(msg, ILogType.WARNING);
	}

	public static void addEvent(String msg)
	{
		AddDebugLogAction.update(msg, ILogType.EVENT);
	}

	private static void read()
	{

	}

	private static void save()
	{

	}

	private static void append()
	{

	}

	private static void clean()
	{

	}
}