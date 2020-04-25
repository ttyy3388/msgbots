package org.beuwi.simulator.managers;

import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialogBox;
import org.beuwi.simulator.platform.ui.components.ILogItem;
import org.beuwi.simulator.platform.ui.components.ILogType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogManager
{
	public static void addError(String message)
	{
		// AddDebugLogAction.update(message, ILogType.ERROR);
	}

	public static void addWarning(String message)
	{
		// AddDebugLogAction.update(message, ILogType.WARNING);
	}

	public static void addEvent(String message)
	{
		// AddDebugLogAction.update(message, ILogType.EVENT);
	}

	// 인자 없으면 Global Log 로 인식

	// Global Log
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
			JSONArray array = (JSONArray) new JSONParser().parse(FileManager.read(file));

			for (Object object : array)
			{
				JSONObject data = (JSONObject) object;

				list.add
				(
					new ILogItem
					(
						(String) data.get("a"),
						(String) data.get("c"),
						switch (Integer.valueOf("" + data.get("b")))
						{
							case 0  -> ILogType.ERROR;
							case 1  -> ILogType.EVENT;
							case 2  -> ILogType.DEBUG;
							default -> null;
						}
					)
				);
			}

			return list;
		}
		catch (Exception e)
		{
			new ShowErrorDialogBox(e).display();
		}

		return new ArrayList<>();
	}

	public static void save()
	{

	}

	public static void append()
	{

	}

	public static void clean()
	{

	}
}