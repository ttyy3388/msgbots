package org.beuwi.msgbots.openapi;

import org.beuwi.msgbots.platform.app.utils.FileUtils;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.util.Map;

public class JSONObject extends org.json.simple.JSONObject
{
	public JSONObject()
	{
		super();
	}

	public JSONObject(File file)
	{
		try
		{
			this.putAll((org.json.simple.JSONObject) new JSONParser().parse(FileUtils.read(file)));
		}
		catch (Exception e)
		{
			// new ShowErrorDialog(e).display();
		}
	}

	public org.json.simple.JSONObject getJSONObject(String type)
	{
		return (org.json.simple.JSONObject) this.get(type);
	}

	public double getDouble(String type)
	{
		return Double.valueOf("" + this.get(type));
	}

	public Object getObject(Object type)
	{
		return this.get(type);
	}

	public String getString(String type)
	{
		return String.valueOf("" + this.get(type));
	}

	public int getInt(String type)
	{
		return Integer.valueOf("" + get(type));
	}

	public boolean getBoolean(String type)
	{
		return Boolean.valueOf("" + get(type));
	}

	public void putString(String type, String data)
	{
		this.put(type, data);
	}
	
	public void putDouble(String type, double data)
	{
		this.put(type, data);
	}

	public void putInt(String type, int data)
	{
	    this.put(type, data);
	}

	public void putBoolean(String type, boolean data)
	{
		this.put(type, data);
	}

	public void putMap(Map map)
	{
		this.putAll(map);
	}

	public String toBeautifyString()
	{
		String json = this.toJSONString();

		StringBuilder builder = new StringBuilder();
		int len = json.length(), i = 0;
		String tab = "";
		char data;
		boolean beginEnd = true;

		for(i = 0 ; i < len ; i ++)
		{
			data = json.charAt(i);

			switch (data)
			{
				case '{': case '[':
				{
					builder.append(data);

					if (beginEnd)
					{
						tab += "\t";
						builder.append("\n");
						builder.append(tab);
					}

					break;
				}
				case '}': case ']':
				{
					if (beginEnd)
					{
						tab = tab.substring(0, tab.length()-1);
						builder.append("\n");
						builder.append( tab );
					}

					builder.append(data);
					break;
				}
				case '"':
				{
					if (json.charAt(i - 1) != '\\')
					{
						beginEnd = !beginEnd;
					}

					builder.append(data);
					break;
				}
				case ',':
				{
					builder.append(data);

					if (beginEnd)
					{
						builder.append("\n");
						builder.append( tab );
					}

					break;
				}
				default :
				{
					builder.append(data);
				}
			}
		}

		return builder.toString();
	}
}