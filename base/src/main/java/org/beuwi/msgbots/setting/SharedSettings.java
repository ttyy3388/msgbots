package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.base.type.ConfigType;

public class SharedSettings {

	// 아래 주석의 주소(Address)들은 이전에 쓰던 방식임
	// "type.head.value" > "global.program.startAutoCompile"
	// "type:name:option" > "project.name.ignore_api_off"

	public static <T> T getData(ConfigType type, String address) {
		String[] data = address.split("\\.");

		// String type = data[0];
		String name = data[0];
		String option = data[1];

		if (ConfigType.GLOBAL.equals(type)) {
			return GlobalSettings.getData(address);
		}
		if (ConfigType.PROJECT.equals(type)) {
			return ProjectSettings.get(name).getData(option);
		}

		throw new NullPointerException("wrong access");
	}

	public static <T> void setData(ConfigType type, String address, T value) {
		String[] data = address.split("\\.");

		// String type = data[0];
		String name = data[0];
		String option = data[1];

		switch (type) {
			case GLOBAL: GlobalSettings.setData(address, value); break;
			case PROJECT: ProjectSettings.get(name).setData(option, value); break;
			case CONTROL: break;
		}
	}

	public static int getInt(ConfigType type, String address) {
		return Integer.valueOf("" + getData(type, address));
	}

	public static String getString(ConfigType type, String address) {
		return String.valueOf("" + getData(type, address));
	}

	public static boolean getBoolean(ConfigType type, String address) {
		return Boolean.valueOf("" + getData(type, address));
	}
}