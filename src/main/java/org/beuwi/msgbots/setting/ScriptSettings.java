package org.beuwi.msgbots.setting;

import java.io.File;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JSONObject;

public class ScriptSettings {
	// Name : Script Name
	public static ScriptSettings get(String name) {
        return new ScriptSettings(name);
    }

    private final JSONObject json;
    private final File file;

    private ScriptSettings(String name) {
        file = FileManager.getBotSetting(name);
        json = new JSONObject(file);
    }

	public <T> T getData(String address) {
		return (T) json.get(address);
	}

	public int getInt(String address) {
		return Integer.valueOf("" + getData(address));
	}

	public String getString(String address) {
		return String.valueOf("" + getData(address));
	}

	public boolean getBoolean(String address) {
		return Boolean.valueOf("" + getData(address));
	}

	public <T> void setData(String address, T value) {
		// 기본 타입이 아닐 경우
		if (!(value instanceof String ||
				value instanceof Double ||
				value instanceof Integer ||
				value instanceof Long)) {
			json.put(address, value.toString());
		}
		else {
			json.put(address, value);
		}

		FileManager.save(file, json.toString());
	}
}