package org.beuwi.msgbots.platform.app.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtils
{

	public static String save(File file, String content)
	{
		try
		{
			file.createNewFile();

			if (content != null)
			{
				if (content.substring(content.length() -1) != System.lineSeparator())
				{
					content += System.getProperty("line.separator");
				}
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF8"));
			bufferedWriter.write(content);
			bufferedWriter.close();

			return content;
		}
		catch (Exception e)
		{
			// new ShowErrorDialog(e).display();
		}

		return null;
	}

	public static String append(File file, String content)
	{
		try
		{
			file.createNewFile();

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
			bufferedWriter.write(content);
			bufferedWriter.close();

			return content;
		}
		catch (Exception e)
		{
			// new ShowErrorDialog(e).display();
		}

		return null;
	}

	public static String read(File file)
	{
		try
		{
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
			// new ShowErrorDialog(e).display();
		}

		return null;
	}

	public static boolean remove(File file)
	{
		try
		{
			if (!file.exists())
			{
				return false;
			}

			if (file.isDirectory())
			{
				// 폴더는 안의 파일들 제거하고 폴더를 제거해야 함.
				for (File data : file.listFiles())
				{
					data.delete();
				}

				return file.delete();
			}

			return file.delete();
		}
		catch (Exception e)
		{
			// new ShowErrorDialog(e).display();
		}

		return false;
	}
}