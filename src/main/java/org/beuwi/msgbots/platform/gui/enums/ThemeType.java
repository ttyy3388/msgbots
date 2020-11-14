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

	BLACK
	{
		@Override
		public String toString()
		{
			return "black";
		}
	},

	WHITE
	{
		@Override
		public String toString()
		{
			return "white";
		}
	};

	public static <T extends Enum<T>> ThemeType convert(String value)
	{
		return valueOf(value.toUpperCase());
	}
}
