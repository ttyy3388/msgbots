package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.openapi.JSONArray;
import org.beuwi.msgbots.openapi.JSONObject;
import org.beuwi.msgbots.platform.app.utils.FileUtils;
import org.beuwi.msgbots.platform.app.view.actions.AddBotLogItemAction;
import org.beuwi.msgbots.platform.gui.control.Log;
import org.beuwi.msgbots.platform.gui.control.Log.Type;
import org.beuwi.msgbots.platform.gui.control.LogView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogManager
{
	public static LogView getView(String name)
	{
		return null; // GlobalLogTab.data.get(name);
	}

	public static List<Log> load()
	{
		return load(FileManager.getDataFile("global_log.json"));
	}

	public static List<Log> load(String name)
	{
		return load(FileManager.getBotLog(name));
	}

	// file : log file
	public static List<Log> load(File file)
	{
		try
		{
			if (!file.exists())
			{
				return new ArrayList();
			}

			List<Log> list = new ArrayList<>();

			JSONArray array = new JSONArray(file);

			for (Object object : array)
			{
				JSONObject data = (JSONObject) object;

				list.add
				(
					new Log
					(
						String.valueOf(data.get("a")),
						String.valueOf(data.get("c")),
						switch (String.valueOf(data.get("b")))
						{
							case "1" -> Type.EVENT;
							case "2" -> Type.DEBUG;
							case "3" -> Type.ERROR;
							default  -> Type.EVENT;
						}
					)
				);
			}

			return list;
		}
		catch (Exception e)
		{
			// AddToastMessageAction.execute(e);
		}

		return new ArrayList<>();
	}

	public static void append(String data, Type type)
	{
		append(FileManager.getDataFile("global_log.json"), data, type, true);
	}

	public static void append(String name, String data, Type type)
	{
		append(FileManager.getBotLog(name), data, type, false);
	}

	public static void append(File file, String data, Type type, boolean isGlobal)
	{
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JSONObject object = new JSONObject();

		object.put("a", data);
		object.put("b", switch (type) {
			case EVENT -> 1;
			case DEBUG -> 2;
			case ERROR -> 3;
		});
		object.put("c", date);

		JSONArray array = new JSONArray(file);

		array.add(object);

		if (isGlobal)
		{
			AddBotLogItemAction.execute(new Log(data, date, type));
		}
		else
		{
			AddBotLogItemAction.execute(FileManager.getBaseName(file.getName()), new Log(data, date, type));
		}

		// FileUtils.save(file, array.toJSONString());
	}

	public static void clear()
	{
		clear(FileManager.getDataFile("global_log.json"));
	}

	public static void clear(String name)
	{
		clear(FileManager.getBotLog(name));
	}

	// file : log file
	public static void clear(File file)
	{
		FileUtils.save(file, "[]");
	}
}