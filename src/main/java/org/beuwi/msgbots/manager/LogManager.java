package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.openapi.JSONArray;
import org.beuwi.msgbots.platform.app.view.actions.InputDetailLogAction;
import org.beuwi.msgbots.platform.gui.control.LogItem;
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
		appendGlobal(LogType.EVENT, data);
	}

	public static void debug( String data) {
		appendGlobal(LogType.DEBUG, data);
	}

	public static void error(String data) {
		appendGlobal(LogType.ERROR, data);
	}

	/* ----------------------------------------------------------------------------------- */

	// Load Global Log
	public static List<LogItem> loadGlobal() {
		return LogManager.load(SharedValues.getFile("GLOBAL_LOG_FILE"));
	}
	// Append Global Log
	public static void appendGlobal(LogType type, String data) {
		LogManager.append(type, SharedValues.getFile("GLOBAL_LOG_FILE"), data, true);
	}
	// Clear Global Log
	public static void clearGlobal() {
		LogManager.clear(SharedValues.getFile("GLOBAL_LOG_FILE"));
	}

	// Load Bot Log
	public static List<LogItem> load(String name) {
		return LogManager.load(FileManager.getBotLog(name));
	}
	// Append Bot Log
	public static void append(LogType type, String name, String data) {
		LogManager.append(type, FileManager.getBotLog(name), data, false);
	}
	// Clear Bot Log
	public static void clear(String name) {
		LogManager.clear(FileManager.getBotLog(name));
	}

	/* ----------------------------------------------------------------------------------- */

	// file : log file
	private static List<LogItem> load(File file) {
		try {
			// 파일이 없거나 제거됐다면 파일 생성
			if (!file.exists()) {
				FileManager.save(file, "[]");
			}

			List<LogItem> list = new ArrayList<>();
			JSONArray array = new JSONArray(file);

			for (Object object : array) {
				JSONObject json = (JSONObject) object;
				if (!object.toString().equals("{}") || (json.size() > 0)) {
					list.add(new LogItem((JSONObject) object));
				}
			}

			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	private static void append(LogType type, File file, String data, boolean global) {
		// 파일이 없거나 제거됐다면 파일 생성
		if (!file.exists()) {
			FileManager.save(file, "[]");
		}

		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JSONObject object = new JSONObject();
		object.put("type", type.toString());
		object.put("data", data);
		object.put("date", date);

		JSONArray array = new JSONArray(file);
		array.add(object);

		if (global) {
			// AddBotLogItemAction.execute(new LogItem(type, data, date));
		}
		else {
			/* AddBotLogItemAction.execute(
			    FileManager.getBaseName(file.getName()),
                new LogItem(type, data, date)
            ); */
		}

		FileManager.save(file, array.toJSONString());
	}

	// file : log file
	private static void clear(File file) {
		FileManager.save(file, "[]");
	}
}