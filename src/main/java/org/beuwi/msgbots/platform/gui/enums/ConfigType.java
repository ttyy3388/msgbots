package org.beuwi.msgbots.platform.gui.enums;

public enum ConfigType
{
	GLOBAL
	{
		@Override
		public String toString()
		{
			return "global";
		}
	},

	SCRIPT
	{
		@Override
		public String toString()
		{
			return "script";
		}
	}
}