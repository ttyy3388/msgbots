package org.beuwi.msgbots.openapi;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.List;

public class JsonArray extends org.json.simple.JSONArray
{
	public JsonArray()
	{
		super();
	}

	public JsonArray(File file)
	{
		try
		{
			this.addAll((List) new JsonParser().parse(FileManager.read(file)));
		}
		catch (Exception e)
		{
			AddToastMessageAction.execute(e);
		}
	}
}