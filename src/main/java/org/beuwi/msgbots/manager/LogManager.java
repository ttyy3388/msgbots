package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.openapi.JsonArray;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.platform.app.view.actions.AddBotLogAction;
import org.beuwi.msgbots.platform.gui.control.Log;
import org.beuwi.msgbots.platform.gui.enums.LogType;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogManager
{
	// Load Global Log
	public static List<Log> load()
	{
		return LogManager.load(SharedValues.GLOBAL_LOG_FILE);
	}

	// Load Bot Log
	public static List<Log> load(String name)
	{
		return LogManager.load(FileManager.getBotLog(name));
	}

	// Append Global Log
	public static void append(String data, LogType type)
	{
		LogManager.append(FileManager.getDataFile(SharedValues.GLOBAL_LOG_PATH), data, type, true);
	}

	// Append Bot Log
	public static void append(String name, String data, LogType type)
	{
		LogManager.append(FileManager.getBotLog(name), data, type, false);
	}

	// Clear Global Log
	public static void clear()
	{
		LogManager.clear(FileManager.getDataFile(SharedValues.GLOBAL_LOG_PATH));
	}

	// Clear Bot Log
	public static void clear(String name)
	{
		LogManager.clear(FileManager.getBotLog(name));
	}

	/* ----------------------------------------------------------------------------------- */

	// file : log file
	public static List<Log> load(File file)
	{
		try
		{
			if (!file.exists())
			{
				return null;
			}

			List<Log> list = new ArrayList<>();

			JsonArray array = new JsonArray(file);

			for (Object object : array)
			{
				list.add(new Log((JsonObject) object));
			}

			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	public static void append(File file, String data, LogType type, boolean global)
	{
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JsonObject object = new JsonObject();

		object.put("a", data);
		object.put("b", switch (type)
		{
			case EVENT -> 1;
			case DEBUG -> 2;
			case ERROR -> 3;
		});
		object.put("c", date);

		JsonArray array = new JsonArray(file);

		array.add(object);

		if (global)
		{
			AddBotLogAction.execute(new Log(type, data, date));
		}
		else
		{
			AddBotLogAction.execute(FileManager.getBaseName(file.getName()), new Log(type, data, date));
		}

		FileManager.save(file, array.toJSONString());
	}

	// file : log file
	public static void clear(File file)
	{
		FileManager.save(file, "[]");
	}
}