package org.beuwi.simulator.settings;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.beuwi.simulator.platform.openapi.IJSONObject;
import org.beuwi.simulator.platform.openapi.IJSONParser;

import java.io.File;
import java.util.Map;

public class Settings
{
	public static Public getPublicSetting(String type)
	{
		return new Public(type);
	}

	public static Script getScriptSetting(String name)
	{
		return new Script(name);
	}

	public static class Public extends IJSONObject
	{
		private String type;
		private File file;

		public Public(String type)
		{
			try
			{
				this.file = FileManager.getDataFile("global_setting.json");
				this.type = type;

				this.putAll(new IJSONObject(file).getJSONObject(type));
			}
			catch (Exception e)
			{
				new ShowErrorDialog(e).display();
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
				IJSONObject data = new IJSONObject(file);

				data.put(type, this);

				FileManager.save(file, data.toBeautifyString());
			}
			catch (Exception e)
			{
				new ShowErrorDialog(e).display();
			}
		}
	}

	public static class Script extends IJSONObject
	{
		private File file;

		public Script(String name)
		{
			try
			{
				this.file = FileManager.getBotSetting(name);

				this.putAll(new IJSONParser().parse(file));
			}
			catch (Exception e)
			{
				new ShowErrorDialog(e).display();
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
	}
}