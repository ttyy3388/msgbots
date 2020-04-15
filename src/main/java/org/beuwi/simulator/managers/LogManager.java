package org.beuwi.simulator.managers;

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

	/* // Global Log
	private static ArrayList<ILogItem> load()
	{
		return load(FileManager.getDataFile("global_log.json"));
	}

	// Bot Log
	private static ArrayList<ILogItem> load(String name)
	{
		return load(FileManager.getBotLog(name));
	}

	private static ArrayList<ILogItem> load(File file)
	{
		try
		{
			if (!file.exists())
			{
				return new ArrayList();
			}

			ArrayList<ILogItem> list = new ArrayList<>();
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
						(int) data.get("b")
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

	private static void save()
	{

	}

	private static void append()
	{

	}

	private static void clean()
	{

	} */
}