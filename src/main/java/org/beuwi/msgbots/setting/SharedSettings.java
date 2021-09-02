package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.base.type.ConfigType;

public class SharedSettings {

	// address : "type.head.value" > "global.program.startAutoCompile"
	// Address : "type:name:option" > "project.name.ignore_api_off"

	public static <T> T getData(String address) {
		String[] data = address.split("\\.");

		String type = data[0];
		String head = data[1];
		String option = data[2];

		switch (ConfigType.convert(type)) {
			case GLOBAL -> GlobalSettings.getData(head + "." + option);
			case PROJECT ->  ProjectSettings.getSetting(head).getData(option);
		}

		throw new NullPointerException("this address does not exists");
	}

	public static <T> void setData(String address, T value) {
		String[] data = address.split("\\.");

		String type = data[0];
		String head = data[1];
		String option = data[2];

		switch (ConfigType.convert(type)) {
			case GLOBAL -> GlobalSettings.setData(head + "." + option, value);
			case PROJECT ->  ProjectSettings.getSetting(head).setData(option, value);
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