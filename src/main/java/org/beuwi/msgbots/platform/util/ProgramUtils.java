package org.beuwi.msgbots.platform.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ProgramUtils
{
	public static String getIDType(String id)
	{
		return id.split("@")[1].split("::")[0];
	}

	public static String getIDName(String id)
	{
		return id.split("@")[1].split("::")[1];
	}

	public static String getErrorContent(Exception error)
	{
		StringWriter message = new StringWriter();
		error.printStackTrace(new PrintWriter(message));
		return message.toString();
	}
}