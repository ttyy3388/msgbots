package org.beuwi.msgbots.compiler.api;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

import java.io.*;

public class FileStream extends ScriptableObject
{
    @Override
    public String getClassName() 
    {
        return "FileStream";
    }

	@JSStaticFunction
	public static String read(String path)
	{
		try
		{
			File file = new File(path);

			if (!file.exists())
			{
				return null;
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = "", text = reader.readLine();

			while ((line = reader.readLine()) != null)
			{
				text += "\n" + line;
			}

			reader.close();

			return text;
		}
		catch (Exception e)
		{
			Context.reportError(e.toString());
		}

		return null;
	}


	@JSStaticFunction
	public static String write(String path, String data)
	{
		try
		{
			File file = new File(path);

			file.getParentFile().mkdirs();
			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, false), "UTF8"));
			writer.write(data);
			writer.close();

			return data;
		}
		catch(Exception e)
		{
			Context.reportError(e.toString());
		}

		return null;
	}

	@JSStaticFunction
	public static String append(String path, String data)
	{
		try
		{
			File file = new File(path);

			file.getParentFile().mkdirs();
			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
			writer.write(data);
			writer.close();

			return data;
		}
		catch(Exception e)
		{
			Context.reportError(e.toString());
		}

		return null;
	}
	
	@JSStaticFunction
    public static Boolean remove(String path)
	{
    	try
    	{
			File file = new File(path);

			if (!file.exists())
			{
				return false;
			}

			return file.delete();
    	}
		catch(Exception e)
		{
			Context.reportError(e.toString());
		}

		return null;
    }
}