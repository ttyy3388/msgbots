package org.beuwi.simulator.managers;

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

	// 인자 없으면 Global Log 로 인식
	public static List<ILogItem> load()
	{
		return load(FileManager.getDataFile("global_log.json"));
	}

	// Bot Log
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

	public static void append(String name, String data, int type)
	{
		File file = FileManager.getBotLog(name);
		String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());

		JSONObject object = new JSONObject();

		object.put("a", name);
		object.put("b", type);
		object.put("c", time);

		JSONArray array = new IJSONArray(file);

		array.add(object);

		System.out.println(array.toJSONString());
	}

	public static void clear(String name)
	{
		FileManager.save(FileManager.getBotLog(name), "[]");
	}
}