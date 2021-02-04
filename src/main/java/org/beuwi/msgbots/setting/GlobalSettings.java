package org.beuwi.msgbots.setting;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JSONObject;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GlobalSettings {
	private static final File file = SharedValues.GLOBAL_CONFIG_FILE;
	private static JSONObject json = new JSONObject(file);

	private static final List<InvalidationListener> listeners = new ArrayList<>();

	// 파일 변경 이벤트 시 호출
	// private static final BooleanProperty changedProperty = new SimpleBooleanProperty(false);

	static {
		FileManager.link(file, () -> {
			json = new JSONObject(file);
		});
		/* FileManager.link(file, () -> {
			changedProperty.setValue(true);
		}); */
	}

	/* public static ReadOnlyBooleanProperty changedProperty() {
		return changedProperty;
		// listeners.add(listener);
	} */

	/* public static ReadOnlyBooleanProperty changedProperty(String address) {

	} */

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