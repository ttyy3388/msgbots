package org.beuwi.msgbots.platform.gui.enums;

public enum NoticeType
{
	EVENT
	{
		@Override
		public String toString()
		{
			return "event";
		}
	},

	ERROR
	{
	    @Override
		public String toString()
		{
			return "error";
		}
	},

    WARNING
	{
        @Override
        public String toString()
        {
            return "warning";
        }
    }
}
