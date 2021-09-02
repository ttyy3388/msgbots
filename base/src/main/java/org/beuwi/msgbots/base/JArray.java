package org.beuwi.msgbots.base;

import org.beuwi.msgbots.manager.FileManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
				// JSONObject를 자체 제작한 JObject로 변환함
				// 이로써 더이상 JSONObejct를 사용할 필요가 없어지도록 함
				Object object = new JSONParser().parse(data);
				if (object instanceof JSONObject) {
					// Convert to JObject
					JSONObject json = (JSONObject) object;
					JObject jobject = new JObject();
					jobject.putAll(json);
					this.addAll((List) jobject);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}