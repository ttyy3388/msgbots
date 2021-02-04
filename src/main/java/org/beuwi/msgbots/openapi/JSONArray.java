package org.beuwi.msgbots.openapi;

import org.beuwi.msgbots.manager.FileManager;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.List;

public class JSONArray extends org.json.simple.JSONArray {
	public JSONArray() {
		super();
	}

	public JSONArray(File file) {
		try {
			this.addAll((List) new JSONParser().parse(FileManager.read(file)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}