package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.openapi.FileListener;
import org.beuwi.msgbots.openapi.FileObserver;
import org.beuwi.msgbots.platform.app.view.actions.AddToastMessageAction;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileManager {
	public static String getBaseName(String name) {
		return name.contains(".") ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	public static String getExtension(String name) {
		return name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
	}

	public static File getDataFile(String name) {
		return new File(SharedValues.DATA_FOLDER_FILE + File.separator + name);
	}

	public static File[] getBotList() {
		return SharedValues.BOTS_FOLDER_FILE.listFiles();
	}

	public static File[] getBotFiles() {
		return SharedValues.BOTS_FOLDER_FILE.listFiles();
	}

	public static String[] getBotNames() {
		File[] files = SharedValues.BOTS_FOLDER_FILE.listFiles(File::isDirectory);
		String[] names = new String[files.length];

		for (int i = 0 ; i < files.length ; i ++) {
			names[i] = files[i].getName();
		}

		return names;
	}

	public static File getBotFolder(String name) {
		return new File(SharedValues.BOTS_FOLDER_FILE + File.separator  + getBaseName(name));
	}

	public static File getBotScript(String name) {
		return new File(getBotFolder(name).getPath() + File.separator + "index.js");
	}

	public static File getBotSetting(String name) {
		return new File(getBotFolder(name).getPath() + File.separator + "bot.json");
	}

	public static File getBotLog(String name) {
		return new File(getBotFolder(name).getPath() + File.separator + "log.json");
	}

	/* ----------------------------------------------------------------------------------- */

	public static FileObserver link(File file, FileListener listener) {
		FileObserver observer = new FileObserver(file);

		observer.addListener(listener);

		return observer;
	}

	public static String save(File file, String content) {
		try {
			file.createNewFile();

			if (content != null) {
				if (content.substring(content.length() -1) != System.lineSeparator()) {
					content += System.getProperty("line.separator");
				}
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF8"));
			bufferedWriter.write(content);
			bufferedWriter.close();

			return content;
		}
		catch (Exception e) {
			AddToastMessageAction.execute(e);
		}

		return null;
	}

	public static String append(File file, String content) {
		try {
			file.createNewFile();

			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
			bufferedWriter.write(content);
			bufferedWriter.close();

			return content;
		}
		catch (Exception e) {
			AddToastMessageAction.execute(e);
		}

		return null;
	}

	public static String read(File file) {
		try {
			if (!file.exists()) {
				return null;
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = "", text = bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				text += "\n" + line;
			}

			bufferedReader.close();

			return text;
		}
		catch (Exception e) {
			AddToastMessageAction.execute(e);
		}

		return null;
	}

	public static boolean remove(File file) {
		try {
			if (!file.exists()) {
				return false;
			}

			if (file.isDirectory()) {
				// 폴더는 안의 파일들 제거하고 폴더를 제거해야 함.
				for (File data : file.listFiles()) {
					data.delete();
				}

				return file.delete();
			}

			return file.delete();
		}
		catch (Exception e) {
			AddToastMessageAction.execute(e);
		}

		return false;
	}
}