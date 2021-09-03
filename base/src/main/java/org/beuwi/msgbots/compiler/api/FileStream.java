package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.shared.SharedValues;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.annotations.JSStaticFunction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileStream extends ScriptableObject {
    @Override
    public String getClassName() {
        return "FileStream";
    }

	// Save 폴더에 저장되도록
    private static String covertPath(String path) {
    	return SharedValues.getString("path.saveFolder") + File.separator + path;
	}

	@JSStaticFunction
	public static String read(String path) {
    	try {
			File file = new File(covertPath(path));
			if (!file.exists()) {
				return null;
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line = "", text = reader.readLine();

			while ((line = reader.readLine()) != null) {
				text += "\n" + line;
			}
			reader.close();

			return text;
		}
    	catch (Exception e) {
			Context.reportError(e.toString());
		}
    	return null;
	}

	@JSStaticFunction
	public static String write(String path, String data) {
    	try {
			File file = new File(covertPath(path));
			file.getParentFile().mkdirs();
			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF8"));
			writer.write(data);
			writer.close();

			return data;
		}
		catch (Exception e) {
			Context.reportError(e.toString());
		}
		return null;
	}

	@JSStaticFunction
	public static String append(String path, String data) {
		try {
			File file = new File(covertPath(path));
			file.getParentFile().mkdirs();
			file.createNewFile();

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));
			writer.write(data);
			writer.close();

			return read(path);
		}
		catch (Exception e) {
			Context.reportError(e.toString());
		}
		return null;
	}
	
	@JSStaticFunction
    public static Boolean remove(String path) {
		try {
			File file = new File(covertPath(path));
			if (!file.exists()) {
				return false;
			}
			return file.delete();
		}
		catch(Exception e) {
			Context.reportError(e.toString());
		}
		return null;
    }

    @JSStaticFunction
	public static Boolean create(String path) {
		try {
			File file = new File(covertPath(path));
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		catch (Exception e) {
			Context.reportError(e.toString());
		}
		return null;
	}
}