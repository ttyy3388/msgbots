package org.beuwi.msgbots.setting;

import java.io.File;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.JsonObject;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.beuwi.msgbots.platform.util.SharedValues;

public class ScriptSettings
{
	// Name : Script Name
	public static ScriptSettings get(String name)
    {
        return new ScriptSettings(name);
    }

    private final JsonObject json;
    private final File file;

    private ScriptSettings(String name)
    {
        file = FileManager.getBotSetting(name);
        json = new JsonObject(file);
    }

	public <T> T getData(String address)
	{
		return (T) json.get(address);
	}

	public int getInt(String address)
	{
		return Integer.valueOf("" + getData(address));
	}

	public String getString(String address)
	{
		return String.valueOf("" + getData(address));
	}

	public boolean getBoolean(String address)
	{
		return Boolean.valueOf("" + getData(address));
	}

	public <T> void setData(String address, T value)
	{
		json.put(address, value);

		try
		{
			FileManager.save(file, json.toString());
		}
		catch (Exception e)
		{
			AddToastMessageAction.execute(e);
		}
	}
}