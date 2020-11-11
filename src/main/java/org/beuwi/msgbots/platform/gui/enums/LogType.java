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
}