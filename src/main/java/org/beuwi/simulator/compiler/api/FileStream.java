package org.beuwi.simulator.compiler.api;

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

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = "", text = bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null)
			{
				text += "\n" + line;
			}

			bufferedReader.close();

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

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, false), "UTF8"));
			bufferedWriter.write(data);
			bufferedWriter.close();

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

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
			bufferedWriter.write(data);
			bufferedWriter.close();

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