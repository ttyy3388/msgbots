package org.beuwi.simulator.compiler.api;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSFunction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileStream extends ScriptableObject
{
	public FileStream(ScriptableObject object)
	{
		super(object, object.getPrototype());
	}

    @Override
    public String getClassName() 
    {
        return "FileStream";
    }

	@JSFunction
	public String read(String path)
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


	@JSFunction
	public String write(String path, String data)
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

	@JSFunction
	public String append(String path, String data)
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
	
	@JSFunction
    public Boolean remove(String path)
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