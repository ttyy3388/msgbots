package org.beuwi.simulator.settings;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.utils.JsonEnterConvert;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;

public class Settings
{
	public static Public getPublicSetting(String getType)
	{
		return new Public(getType);
	}

	public static Script getScriptSetting(String getName)
	{
		return new Script(getName);
	}

	public static class Public extends JSONObject
	{
		private File file;

		public Public(String getType)
		{
			try
			{
				file = FileManager.getDataFile("global_setting.json");
				putAll((JSONObject) ((JSONObject) new JSONParser().parse(FileManager.read(file))).get(getType));
			}
			catch (Exception e)
			{
				// new ShowErrorDialog(e).display();
			}
		}

		public String getString(String getType)
		{
			return String.valueOf("" + get(getType));
		}

		public int getInt(String getType)
		{
			return Integer.valueOf("" + get(getType));
		}

		public boolean getBoolean(String getType)
		{
			return Boolean.valueOf("" + get(getType));
		}

		public void putString(String setType, String setData)
		{
			put(setType, setData); apply();
		}

		public void putInt(String setType, int setData)
		{
			put(setType, setData); apply();
		}

		public void putBoolean(String setType, boolean setData)
		{
			put(setType, setData); apply();
		}

		private void apply()
		{
			FileManager.save(file, JsonEnterConvert.convert(toJSONString()));
		}
	}

	public static class Script extends JSONObject
	{
		private File file;

		public Script(String name)
		{
			try
			{
				file = FileManager.getBotSetting(name);

				putAll((JSONObject) new JSONParser().parse(FileManager.read(file)));
			}
			catch (Exception e)
			{
				// new ShowErrorDialog(e).display();
			}
		}

		public String getString(String getType)
		{
			return String.valueOf("" + get(getType));
		}

		public int getInt(String getType)
		{
			return Integer.valueOf("" + get(getType));
		}

		public boolean getBoolean(String getType)
		{
			return Boolean.valueOf("" + get(getType));
		}

		public void putString(String setType, String setData)
		{
			put(setType, setData); apply();
		}

		public void putInt(String setType, int setData)
		{
			put(setType, setData); apply();
		}

		public void putBoolean(String setType, boolean setData)
		{
			put(setType, setData); apply();
		}

		private void apply()
		{
			FileManager.save(file, JsonEnterConvert.convert(toJSONString()));
		}
	}
}