package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.platform.gui.enums.ConfigType;

public class SharedSettings {

	// Address : "type:name:option" > "global:program:start_auto_compile"
	// Address : "type:name:option" > "script:test1:ignore_api_off"

	public static <T> T getData(String address) {
		String[] data = address.split(":");

		String type = data[0];
		String name = data[1];
		String option = data[2];

		if (type.equals(ConfigType.GLOBAL.toString())) {
			return GlobalSettings.getData(name + ":" + option);
		}
		if (type.equals(ConfigType.SCRIPT.toString())) {
			return ScriptSettings.get(name).getData(option);
		}

		throw new NullPointerException("this address does not exists");
	}

	public static <T> void setData(String address, T value) {
		String[] data = address.split(":");

		String type = data[0];
		String name = data[1];
		String option = data[2];

		if (type.equals(ConfigType.GLOBAL.toString())) {
			GlobalSettings.setData(name + ":" + option, value);
		}
		if (type.equals(ConfigType.SCRIPT.toString())) {
			ScriptSettings.get(name).setData(option, value);
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