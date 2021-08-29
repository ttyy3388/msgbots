package org.beuwi.msgbots.base.manager;

import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.base.file.FileListener;
import org.beuwi.msgbots.base.file.FileObserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 해당 클래스로 접근하는 파일들은 유저 커스텀 파일들임 ([ResourceUtils] 클래스와 반대)
public class FileManager implements Manager {
	public static String getBaseName(File file) {
		return getBaseName(file.getName());
	}
	public static String getBaseName(String name) {
		return name.contains(".") ? name.substring(0, name.lastIndexOf(".")) : name;
	}

	public static String getExtension(File file) {
		return getExtension(file.getName());
	}
	public static String getExtension(String name) {
		return name.contains(".") ? name.substring(name.lastIndexOf(".") + 1) : name;
	}

	/* ----------------------------------------------------------------------------------- */

	public static FileObserver link(File file, FileListener listener) {
		// 파일이 없을 경우는 없지만, 만약을 대비해서 추가함
		if (file == null || !file.exists()) {
			throw new NullPointerException();
		}
		FileObserver observer = new FileObserver(file);
		observer.addListener(listener);
		return observer;
	}

	public static String write(String path, String content) {
		return write(new File(path), content);
	}

	public static String write(File file, String content) {
		try {
			file.createNewFile();

			// 마지막 줄에 개행 추가하는 부분
			if (content != null) {
				if (content.substring(content.length() -1) != System.lineSeparator()) {
					content += System.getProperty("line.separator");
				}
			}

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF8"));
			writer.write(content);
			writer.close();

			return content;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String append(File file, String content) {
		try {
			// file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
			writer.write(content);
			writer.close();

			return content;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String read(File file) {
		try {
			return read(new FileInputStream(file));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String read(InputStream stream) {
		try {
			if (stream == null) {
				return null;
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
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