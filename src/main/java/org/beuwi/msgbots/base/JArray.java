package org.beuwi.msgbots.base;

import org.beuwi.msgbots.manager.FileManager;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class JArray extends JSONArray {
	public JArray() {
		super();
	}
	public JArray(File file) {
		this(FileManager.read(file));
	}
	public JArray(InputStream stream) {
		this(FileManager.read(stream));
	}
	public JArray(String data) {
		if (data == null || data.isEmpty()) {
			// throw new NullPointerException();
			return ;
		}

		try {
			if (data != null) {
				addAll((List) new JSONParser().parse(data));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}