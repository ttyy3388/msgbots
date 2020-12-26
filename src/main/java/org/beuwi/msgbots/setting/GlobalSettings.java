package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;

public class GlobalSettings {
	private static final File file = SharedValues.GLOBAL_CONFIG_FILE;
	private static JsonObject json = new JsonObject(file);

	static {
		FileManager.link(file, () -> json = new JsonObject(file));
	}

	public static <T> T getData(String address) {
		String[] data = address.split(":");

		String name = data[0];
		String option = data[1];

		// throw new NullPointerException("this address does not exists");

		return (T) json.getMap(name).get(option);
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

	public static <T> void setData(String address, T value) {
		String[] data = address.split(":");

		String name = data[0];
		String option = data[1];

		// 기본 타입이 아닐 경우
		if (!(value instanceof String ||
			value instanceof Double ||
			value instanceof Integer ||
			value instanceof Long)) {
			json.getMap(name).put(option, value.toString());
		}
		else {
			json.getMap(name).put(option, value);
		}

		try {
			FileManager.save(file, json.toString());
		}
		catch (Exception e) {
			// 이미 파일 매니저에서 IOException 으로 막기 때문에 에러가 잡힐 거 같진 않음
			e.printStackTrace();
		}
	}
}