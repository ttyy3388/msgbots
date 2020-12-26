package org.beuwi.msgbots.openapi;

import org.beuwi.msgbots.manager.FileManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.util.Map;

public class JsonObject extends JSONObject {

	public JsonObject() {
		this("");
	}

	public JsonObject(File file) {
		this(FileManager.read(file));
	}

	public JsonObject(String data) {
		if (data.isEmpty() || data == null){
			return ;
		}
		try {
			this.putAll((JSONObject) new JSONParser().parse(data));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public JSONObject getJsonObject(String type) {
		return (JSONObject) this.get(type);
	}

	public Map getMap(String type) {
		return (Map) this.get(type);
	}

	public double getDouble(String type) {
		return Double.valueOf("" + this.get(type));
	}

	public Object getObject(Object type) {
		return this.get(type);
	}

	public String getString(String type) {
		return String.valueOf("" + this.get(type));
	}

	public int getInt(String type) {
		return Integer.valueOf("" + get(type));
	}

	public boolean getBoolean(String type) {
		return Boolean.valueOf("" + get(type));
	}

	public void putString(String type, String data) {
		this.put(type, data);
	}

	public void putDouble(String type, double data) {
		this.put(type, data);
	}

	public void putInt(String type, int data) {
		this.put(type, data);
	}

	public void putBoolean(String type, boolean data) {
		this.put(type, data);
	}

	public void putMap(Map map) {
		this.putAll(map);
	}

	@Override
	public String toString() {
		String json = this.toJSONString();

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
					if (json.charAt(i - 1) != '\\')
					{
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