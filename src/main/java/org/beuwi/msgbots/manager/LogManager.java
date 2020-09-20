package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.openapi.JSONArray;
import org.beuwi.msgbots.openapi.JSONObject;
import org.beuwi.msgbots.platform.gui.control.Log;
import org.beuwi.msgbots.platform.gui.control.LogView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LogManager
{
	public static HashMap<String, LogView> data = new HashMap<>();

	public static LogView getView(String name)
	{
		return data.get(name);
	}

	// 인자 없으면 Global Log 로 인식
	public static List<Log> load()
	{
		return load(FileManager.getDataFile("global_log.json"));
	}

	// Bot Log
	public static List<Log> load(String name)
	{
		return load(FileManager.getBotLog(name));
	}

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

				/* list.add
				(
					new Log
					(
						String.valueOf(data.get("a")),
						String.valueOf(data.get("c")),
						Integer.valueOf("" + data.get("b"))
					)
				); */
			}

			return list;
		}
		catch (Exception e)
		{
			// new ShowErrorDialog(e).display();
		}

		return new ArrayList<>();
	}

	public static void append(String name, String data, int type)
	{
		File file = FileManager.getBotLog(name);
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JSONObject object = new JSONObject();

		object.put("a", name);
		object.put("b", type);
		object.put("c", date);

		JSONArray array = new JSONArray(file);

		array.add(object);

		// AddBotLogItemAction.update(name, new ILogItem(data, date, type));

		System.out.println(array.toJSONString());
	}

	public static void clear(String name)
	{
		// FileManager.save(FileManager.getBotLog(name), "[]");
	}
}