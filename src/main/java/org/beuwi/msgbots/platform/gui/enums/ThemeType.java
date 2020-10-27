package org.beuwi.msgbots.platform.gui.enums;

public enum ThemeType
{
	DARK
	{
		@Override
		public String toString()
        {
			return "dark";
		}
	},

	LIGHT
	{
		@Override
		public String toString()
		{
			return "light";
		}
	},
}
