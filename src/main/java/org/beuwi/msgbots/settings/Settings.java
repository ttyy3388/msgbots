package org.beuwi.msgbots.settings;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;
import java.util.Map;

public class Settings
{
	/* public static Public getPublicSetting(String type)
	{
		return new Public(type);
	}

	public static Script getScriptSetting(String name)
	{
		return new Script(name);
	}

	public static class Public extends JSONObject
	{
		private final File file = FileManager.getDataFile(SharedValues.GLOBAL_SETTING_PATH);

		private final String type;

		public Public(String type)
		{
			this.type = type;

			try
			{
				putAll(new JSONObject(file).getJSONObject(type));
			}
			catch (Exception e)
			{
				// new ShowErrorDialog(e).display();
			}
		}

		public void putString(String type, String data)
		{
			super.put(type, data); apply();
		}

		public void putInt(String type, int data)
		{
			this.put(type, data); apply();
		}

		public void putBoolean(String type, boolean data)
		{
			this.put(type, data); apply();
		}

		private void apply()
		{
			try
			{
				JSONObject data = new JSONObject(file);

				data.put(type, this);

				FileManager.save(file, data.toBeautifyString());
			}
			catch (Exception e)
			{
				// new ShowErrorDialog(e).display();
			}
		}
	}

	public static class Script extends JSONObject
	{
		private final File file;

		public Script(String name)
		{
			this.file = FileManager.getBotSetting(name);

			try
			{
				this.putAll(new JSONObject(file));
			}
			catch (Exception e)
			{
				// new ShowErrorDialog(e).display();
			}
		}

		public void putString(String type, String data)
		{
			this.put(type, data); apply();
		}

		public void putInt(String type, int data)
		{
			this.put(type, data); apply();
		}

		public void putBoolean(String type, boolean data)
		{
			this.put(type, data); apply();
		}

		public void putMap(Map map)
		{
			this.putAll(map); apply();
		}

		private void apply()
		{
			FileManager.save(file, this.toBeautifyString());
		}
	} */
}