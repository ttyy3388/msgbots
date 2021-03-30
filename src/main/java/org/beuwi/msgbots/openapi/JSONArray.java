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
		if (file == null) {
			// throw new NullPointerException();
			return ;
		}

		try {
			String text = FileManager.read(file);
			if (text != null) {
				this.addAll((List) new JSONParser().parse(text));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}