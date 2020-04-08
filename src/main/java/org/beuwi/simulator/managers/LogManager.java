package org.beuwi.simulator.managers;

import org.beuwi.simulator.platform.application.views.actions.AddDebugLogAction;
import org.beuwi.simulator.platform.ui.components.ILogType;

public class LogManager
{
	public static void addError(String message)
	{
		AddDebugLogAction.update(message, ILogType.ERROR);
	}

	public static void addWarning(String message)
	{
		AddDebugLogAction.update(message, ILogType.WARNING);
	}

	public static void addEvent(String message)
	{
		AddDebugLogAction.update(message, ILogType.EVENT);
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