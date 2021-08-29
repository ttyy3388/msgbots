package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.base.enums.OptionType;

public class SharedSettings {

	// Address : "type:name:option" > "global:program:start_auto_compile"
	// Address : "type:name:option" > "script:test1:ignore_api_off"

	public static <T> T getData(String address) {
		String[] data = address.split("\\.");

		String type = data[0];
		String head = data[1];
		String option = data[2];

		if (type.equals(OptionType.GLOBAL.toString())) {
			return GlobalSettings.getData(head + "." + option);
		}
		if (type.equals(OptionType.SCRIPT.toString())) {
			return ProjectSettings.get(head).getData(option);
		}

		throw new NullPointerException("this address does not exists");
	}

	public static <T> void setData(String address, T value) {
		String[] data = address.split("\\.");

		String type = data[0];
		String head = data[1];
		String option = data[2];

		if (type.equals(OptionType.GLOBAL.toString())) {
			GlobalSettings.setData(head + "." + option, value);
		}
		if (type.equals(OptionType.SCRIPT.toString())) {
			ProjectSettings.get(head).setData(option, value);
		}
	}

	public static int getInt(String address) {
		return Integer.valueOf("" + getData(address));
	}

	public static String getString(String address) {
		return String.valueOf("" + getData(address));
	}

	public static boolean getBoolean(String address) {
		return Boolean.valueOf("" + getData(address));
	}
}