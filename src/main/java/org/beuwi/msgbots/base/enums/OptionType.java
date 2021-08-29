package org.beuwi.msgbots.base.enums;

public enum OptionType
{
	GLOBAL {
		@Override
		public String toString() {
			return "global";
		}
	},

	SCRIPT {
		@Override
		public String toString() {
			return "script";
		}
	}
}