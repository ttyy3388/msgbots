package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.openapi.JsonArray;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.platform.app.view.actions.AddBotLogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.beuwi.msgbots.platform.gui.control.LogBox;
import org.beuwi.msgbots.platform.gui.enums.LogType;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.json.simple.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogManager {
	public static void event(String data) {
		append(LogType.EVENT, data);
	}

	public static void debug( String data) {
		append(LogType.DEBUG, data);
	}

	public static void error(String data) {
		append(LogType.ERROR, data);
	}

	/* ----------------------------------------------------------------------------------- */

	// Load Global Log
	public static List<LogBox> load() {
		return LogManager.load(SharedValues.GLOBAL_LOG_FILE);
	}

	// Load Bot Log
	public static List<LogBox> load(String name) {
		return LogManager.load(FileManager.getBotLog(name));
	}

	// Append Global Log
	public static void append(LogType type, String data) {
		LogManager.append(type, SharedValues.GLOBAL_LOG_FILE, data, true);
	}

	// Append Bot Log
	public static void append(LogType type, String name, String data) {
		LogManager.append(type, FileManager.getBotLog(name), data, false);
	}

	// Clear Global Log
	public static void clear() {
		LogManager.clear(SharedValues.GLOBAL_LOG_FILE);
	}

	// Clear Bot Log
	public static void clear(String name) {
		LogManager.clear(FileManager.getBotLog(name));
	}

	/* ----------------------------------------------------------------------------------- */

	// file : log file
	public static List<LogBox> load(File file) {
		try {
			if (!file.exists()) {
				return null;
			}

			List<LogBox> list = new ArrayList<>();

			JsonArray array = new JsonArray(file);

			for (Object object : array) {
				list.add(new LogBox((JSONObject) object));
			}

			return list;
		}
		catch (Exception e) {
			AddToastMessageAction.execute(e);
		}

		return new ArrayList<>();
	}

	public static void append(LogType type, File file, String data, boolean global) {
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JsonObject object = new JsonObject();

		object.put("type", type.toString());
		object.put("data", data);
		object.put("date", date);

		JsonArray array = new JsonArray(file);

		array.add(object);

		if (global) {
			AddBotLogBoxAction.execute(new LogBox(type, data, date));
		}
		else {
			AddBotLogBoxAction.execute(FileManager.getBaseName(file.getName()), new LogBox(type, data, date));
		}

		FileManager.save(file, array.toJSONString());

		System.out.println(type + " : " + data + " : " +  date);
	}

	// file : log file
	public static void clear(File file) {
		FileManager.save(file, "[]");
	}
}