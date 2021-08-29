package org.beuwi.msgbots.base.json;

import org.beuwi.msgbots.base.manager.FileManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class JObject extends JSONObject {
	public JObject() {
		this("");
	}

	public JObject(File file) {
		this(FileManager.read(file));
	}

	public JObject(InputStream stream) {
		this(FileManager.read(stream));
	}

	public JObject(String data) {
		if (data == null || data.isEmpty()) {
			// throw new NullPointerException();
			return ;
		}

		try {
			putAll((org.json.simple.JSONObject) new JSONParser().parse(data));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object getObject(Object type) {
		return get(type);
	}
	public Map getMap(String type) {
		return (Map) get(type);
	}
	public double getDouble(String type) {
		return Double.valueOf("" + get(type));
	}
	public String getString(String type) {
		return String.valueOf("" + get(type));
	}
	public int getInt(String type) {
		return Integer.valueOf("" + get(type));
	}
	public boolean getBoolean(String type) {
		return Boolean.valueOf("" + get(type));
	}

	public void putString(String type, String data) {
		put(type, data);
	}
	public void putDouble(String type, double data) {
		put(type, data);
	}
	public void putInt(String type, int data) {
		put(type, data);
	}
	public void putBoolean(String type, boolean data) {
		put(type, data);
	}
	public void putMap(Map map) {
		putAll(map);
	}

	@Override
	public String toString() {
		String json = toJSONString();

		StringBuilder builder = new StringBuilder();
		int len = json.length(), i = 0;
		String tab = "";
		char data;
		boolean beginEnd = true;

		for (i = 0 ; i < len ; i ++) {
			data = json.charAt(i);

			switch (data) {
				case '{': case '[': {
					builder.append(data);
					if (beginEnd) {
						tab += "\t";
						builder.append("\n");
						builder.append(tab);
					}
					break;
				}
				case '}': case ']': {
					if (beginEnd) {
						tab = tab.substring(0, tab.length()-1);
						builder.append("\n");
						builder.append( tab );
					}
					builder.append(data);
					break;
				}
				case '"': {
					if (json.charAt(i - 1) != '\\') {
						beginEnd = !beginEnd;
					}
					builder.append(data);
					break;
				}
				case ',': {
					builder.append(data);
					if (beginEnd)
					{
						builder.append("\n");
						builder.append( tab );
					}
					break;
				}
				default : {
					builder.append(data);
				}
			}
		}

		return builder.toString();
	}
}