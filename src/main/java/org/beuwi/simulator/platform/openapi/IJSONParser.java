package org.beuwi.simulator.platform.openapi;

import org.beuwi.simulator.managers.FileManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;

public class IJSONParser extends JSONParser
{
	public IJSONParser()
	{
		super();
	}

	public JSONObject parse(File file) throws Exception
	{
		return (JSONObject) this.parse(FileManager.read(file));
	}
}