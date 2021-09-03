package org.beuwi.msgbots.base;

import org.beuwi.msgbots.manager.FileManager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JArray extends ArrayList<JObject> {
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
			JSONArray array = new JSONArray();
			List<JSONObject> list = (List) new JSONParser().parse(data);

			// JSONObject 요소들을 자체 제작한 JObject 타입으로 변환함
			// 해당 방법은 비효율적이라고 생각되니 추후 메모리 문제가 생기면 변경할 예정임
			addAll(list.stream().map(JObject::convert).collect(Collectors.toList()));

			// JSONObject를 자체 제작한 JObject로 변환함
			// 이로써 더이상 JSONObejct를 사용할 필요가 없어지도록 함
			/* if (object instanceof JSONObject)
				// Convert to JObject
				JSONObject json = (JSONObject) object;
				JObject jobject = new JObject();
				jobject.putAll(json);
				this.addAll((List) jobject);
			} */
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}