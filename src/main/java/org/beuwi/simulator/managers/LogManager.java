package org.beuwi.simulator.managers;

import org.beuwi.simulator.platform.application.views.actions.AddBotLogItemAction;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.openapi.IJSONArray;
import org.beuwi.simulator.platform.ui.components.ILogItem;
import org.beuwi.simulator.platform.ui.components.ILogView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LogManager
{
	public static HashMap<String, ILogView> data = new HashMap<>();

	public static ILogView getView(String name)
	{
		return data.get(name);
	}

	// Global Log
	public static List<ILogItem> load()
	{
		return load(FileManager.getDataFile("global_log.json"));
	}

	public static List<ILogItem> load(String name)
	{
		return load(FileManager.getBotLog(name));
	}

	public static List<ILogItem> load(File file)
	{
		try
		{
			if (!file.exists())
			{
				return new ArrayList();
			}

			List<ILogItem> list = new ArrayList<>();

			JSONArray array = new IJSONArray(file);

			for (Object object : array)
			{
				JSONObject data = (JSONObject) object;

				list.add
				(
					new ILogItem
					(
						String.valueOf(data.get("a")),
						String.valueOf(data.get("c")),
						Integer.valueOf("" + data.get("b"))
					)
				);
			}

			return list;
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();
		}

		return new ArrayList<>();
	}

	// Global Log
	public static void append(String data, int type)
	{
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

		append(FileManager.getDataFile("global_log.json"), data, date, type);

		AddBotLogItemAction.update(new ILogItem(data, date, type));
	}

	public static void append(String name, String data, int type)
	{
		String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

		append(FileManager.getBotLog(name), data, date, type);

		AddBotLogItemAction.update(name, new ILogItem(data, date, type));
	}

	public static void append(File file, String data, String date, int type)
	{
		JSONObject object = new JSONObject();

		object.put("a", data);
		object.put("b", type);
		object.put("c", date);

		JSONArray array = new IJSONArray(file);

		array.add(object);

		FileManager.save(file, array.toJSONString());
	}

	// Global
	public static void clear()
	{
		clear(FileManager.getDataFile("global_log.json"));
	}

	public static void clear(String name)
	{
		clear(FileManager.getBotLog(name));
	}

	public static void clear(File file)
	{
		FileManager.save(file, "[]");
	}
}