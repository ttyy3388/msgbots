package org.beuwi.simulator.managers;

import org.beuwi.simulator.platform.application.views.dialogs.ShowErrorDialogBox;

import java.io.*;

public class FileManager
{
	final public static File PROGRAM_FOLDER = new File(System.getProperty("user.dir"));
	final public static File DATA_FOLDER = new File(PROGRAM_FOLDER + File.separator + "data");
	final public static File BOTS_FOLDER = new File(PROGRAM_FOLDER + File.separator + "bots");

	// ex.js -> ex
	public static String getFileBaseName(String name)
	{
		return (name.contains(".")) ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	// ex.js -> js
	public static String getFileExtension(String name)
	{
		return (name.contains(".")) ? name.substring(name.lastIndexOf(".") + 1) : name;
	}

	public static File getDataFile(String name)
	{
		return new File(DATA_FOLDER + File.separator + name);
	}

	public static String[] getBotNames()
	{
		File[] files = BOTS_FOLDER.listFiles(File::isDirectory);
		String[] names = new String[files.length];

		for (int i = 0 ; i < files.length ; i ++)
		{
			names[i] = files[i].getName();
		}

		return names;
	}

	public static File getBotFolder(String name)
	{
		return new File(BOTS_FOLDER + File.separator + getFileBaseName(name));
	}

	public static File getBotScript(String name)
	{
		return new File(getBotFolder(name).getPath() + File.separator + "index.js");
	}

	public static File getBotSetting(String name)
	{
		return new File(getBotFolder(name).getPath() + File.separator + "bot.json");
	}

	public static File getBotLog(String name)
	{
		return new File(getBotFolder(name).getPath() + File.separator + "log.json");
	}

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
			new ShowErrorDialogBox(e).display();
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
			new ShowErrorDialogBox(e).display();
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
			new ShowErrorDialogBox(e).display();
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
			new ShowErrorDialogBox(e).display();
		}

		return false;
	}
}