package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.File;

public class GlobalSettings
{
	private static final File file = SharedValues.GLOBAL_CONFIG_FILE;
	private static JsonObject json = new JsonObject(file);

	static
	{
		FileManager.link(file, () -> json = new JsonObject(file));
	}

	public static <T> T getData(String address)
	{
		String[] data = address.split("\\.");

		// throw new NullPointerException("this address does not exists");

		return (T) json.getMap(data[0]).get(data[1]);
	}

	public static int getInt(String address)
	{
		return (int) getData(address);
	}

	public static String getString(String address)
	{
		return (String) getData(address);
	}

	public static boolean getBoolean(String address)
	{
		return (boolean) getData(address);
	}

	public static <T> void setData(String address, T value)
	{
		String[] data = address.split("\\.");

		json.getMap(data[0]).put(data[1], value);

		try
		{
			FileManager.save(file, json.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}