package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.base.enums.LogType;
import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.utils.SharedValues;

public class LogManager {
	public static void info(String data) {
		// appendGlobal(LogType.INFO, data);
	}

	public static void debug( String data) {
		// appendGlobal(LogType.DEBUG, data);
	}

	public static void error(String data) {
		// appendGlobal(LogType.ERROR, data);
	}

	/* ----------------------------------------------------------------------------------- */

	// Append Global Log
	/* public static void appendGlobal(LogType type, String data) {
		Dfile dfile = SharedValues.getDfile("GLOBAL_LOG_DFILE");
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JSONObject object = new JSONObject();
		object.put("type", type.toString());
		object.put("data", data);
		object.put("date", date);

		JSONArray array = new JSONArray(dfile.getData());
		array.add(object);

		dfile.setData(array.toJSONString());
	} */
	// Clear Global Log
	public static void clearGlobal() {
		Dfile dfile = SharedValues.getDfile("GLOBAL_LOG_DFILE");
		dfile.setData("[]");
	}

	public static void append(Project project, LogType type, String data) {
		/* File file = project.getFile("log.json");
		// 파일이 없거나 제거됐다면 파일 생성
		if (!file.exists()) {
			FileManager.write(file, "[]");
		}

		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JSONObject object = new JSONObject();
		object.put("type", type.toString());
		object.put("data", data);
		object.put("date", date);

		JSONArray array = new JSONArray(file);
		array.add(object);

		FileManager.write(file, array.toJSONString()); */
	}

	public static void clear(Project project) {
		FileManager.write(project.getFile("log.json"), "[]");
	}
}