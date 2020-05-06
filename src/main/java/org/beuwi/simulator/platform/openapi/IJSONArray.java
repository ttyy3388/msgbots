package org.beuwi.simulator.platform.openapi;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialog;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.List;

public class IJSONArray extends JSONArray
{
	public IJSONArray()
	{
		super();
	}

	public IJSONArray(File file)
	{
		try
		{
			this.addAll((List) new JSONParser().parse(FileManager.read(file)));
		}
		catch (Exception e)
		{
			new ShowErrorDialog(e).display();
		}
	}
}