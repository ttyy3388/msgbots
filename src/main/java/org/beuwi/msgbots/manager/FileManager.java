package org.beuwi.msgbots.manager;

import org.beuwi.msgbots.openapi.FileListener;
import org.beuwi.msgbots.openapi.FileObserver;
import org.beuwi.msgbots.platform.util.SharedValues;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// 해당 클래스로 접근하는 파일들은 유저 커스텀 파일들임 ("ResourceUtils"와 반대)
public class FileManager {
	public static String getBaseName(File file) {
		return getBaseName(file.getName());
	}
	public static String getExtension(File file) {
		return getExtension(file.getName());
	}

	public static String getBaseName(String name) {
		return name.contains(".") ? name.substring(0, name.lastIndexOf(".")) : name;
	}
	public static String getExtension(String name) {
		return name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
	}

	public static File getDataFile(String name) {
		File file = new File(SharedValues.getString("DATA_FOLDER_PATH") + File.separator + name);
		return file;
	}

	// ResourceUtils.getData로 이전
	/* public static File getDataFile(String name) {
		return new File(SharedValues.DATA_FOLDER_FILE + File.separator + name);
	} */

	public static File[] getBotList() {
		return SharedValues.getFile("BOT_FOLDER_FILE").listFiles();
	}

	public static File[] getBotFiles() {
		return SharedValues.getFile("BOT_FOLDER_FILE").listFiles();
	}

	/* public static String[] getBotNames() {
		File[] files = SharedValues.BOT_FOLDER_FILE.listFiles(File::isDirectory);
		String[] names = new String[files.length];

		for (int i = 0 ; i < files.length ; i ++) {
			names[i] = files[i].getName();
		}

		return names;
	} */

	public static boolean isBotFolder(File folder) {
		String botName = folder.getName();
		// 스크립트, 로그, 셋팅 파일 3가지가 다 있어야 봇 폴더로 인식
		if (FileManager.getBotScript(botName).exists() &&
			FileManager.getBotLog(botName).exists() &&
			FileManager.getBotConfig(botName).exists()) {
			return true;
		}
		else {
			return false;
		}
	}

	public static List<String> getBotNames() {
		File[] files = SharedValues.getFile("BOT_FOLDER_FILE").listFiles(File::isDirectory);
		List<String> names = new ArrayList<>();

		for (File file : files) {
			if (FileManager.isBotFolder(file)) {
				names.add(file.getName());
			}
		}

		return names;
	}

	public static File getBotFolder(String name) {
		return new File(SharedValues.getFile("BOT_FOLDER_FILE") + File.separator  + getBaseName(name));
	}

	public static File getBotScript(String name) {
		return new File(getBotFolder(name).getPath() + File.separator + "index.js");
	}

	public static File getBotConfig(String name) {
		return new File(getBotFolder(name).getPath() + File.separator + "bot.json");
	}

	public static File getBotLog(String name) {
		return new File(getBotFolder(name).getPath() + File.separator + "log.json");
	}

	/* ----------------------------------------------------------------------------------- */

	public static FileObserver link(File file, FileListener listener) {
		/* if (file == null || !file.exists()) {
			return null;
		} */
		FileObserver observer = new FileObserver(file);
		observer.addListener(listener);
		return observer;
	}

	public static String save(File file, String content) {
		try {
			file.createNewFile();

			// 마지막 줄에 개행 추가하는 부분
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}

		return null;
	}

	public static String read(InputStream inputReader) {
		try {
			if (inputReader == null) {
				return null;
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputReader, "UTF-8"));
			String line = "", text = bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {
				text += "\n" + line;
			}

			bufferedReader.close();

			return text;
		}
		catch (Exception e) {
			e.printStackTrace();
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
		// File Not Found Exception ??
		catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}