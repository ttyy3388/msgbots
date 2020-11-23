package org.beuwi.msgbots.platform.gui.enums;

public enum LogType
{
	ERROR
	{
		@Override
	    public String toString()
		{
	        return "error";
		}
	},

	EVENT
	{
		@Override
		public String toString()
		{
			return "event";
		}
	},

	DEBUG
	{
		@Override
		public String toString()
		{
			return "debug";
		}
	};


	public static <T extends Enum<T>> LogType convert(String value)
	{
		return LogType.valueOf(value.toUpperCase());
	}
}