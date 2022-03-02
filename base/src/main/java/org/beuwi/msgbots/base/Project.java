package org.beuwi.msgbots.base;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.beuwi.msgbots.manager.FileManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Project /* implements FXProperty */ {
	private final Map<String, File> files = new HashMap<>();

	private final File directory;
	private final String name;
	private final Session session;

	public Project(File directory) {
		if (!check(directory)) {
			throw new RuntimeException("This directory is not project");
		}
		this.directory = directory;
		this.name = directory.getName();
		for (File file : directory.listFiles()) {
			files.put(file.getName(), file);
		}
		this.session = new Session(this);

		this.initialize();
	}

	private void initialize() {
        setPower(getSetting().getBoolean("power"));
	}

	public static boolean check(File directory) {
		File script = new File(directory + File.separator + "index.js");
		File config = new File(directory + File.separator + "bot.json");
		File log = new File(directory + File.separator + "log.json");
		return script.exists() ||
				config.exists() ||
				log.exists();
	}

	public boolean delete() {
		// 폴더만 제거하면 안되고, 안의 파일들을 다 제거한 후 진행해야함
		File directory = getDirectory();
		File[] files = directory.listFiles();
		for (File file : files) {
			file.delete();
		}
		return directory.delete();
	}

	public final Session getSession() {
		return session;
	}

	public File getDirectory() {
		return directory;
	}
	public File getFile(String name) {
		return files.get(name);
	}

	public String getScript() {
		return FileManager.read(files.get("index.js"));
	}
	public JObject getSetting() {
		return new JObject(files.get("bot.json"));
	}
	public JArray getLog() {
		return new JArray(files.get("log.json"));
	}

	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return name;
	}

	private final Map<Object, Object> properties = new HashMap<>();
	public void setProperty(Object key, Object value) {
		properties.put(key, value);
	}
	public Object getProperty(Object key) {
		return properties.get(key);
	}
	public Map<Object, Object> getProperties() {
		return properties;
	}

	private final BooleanProperty powerProperty = new SimpleBooleanProperty(false);
	public final BooleanProperty powerProperty() {
		return powerProperty;
	}
	public void setPower(boolean value) {
		powerProperty.set(value);
	}
	public boolean getPower() {
		return powerProperty.get();
	}

	private final BooleanProperty compiledProperty = new SimpleBooleanProperty(false);
	public final BooleanProperty compiledProperty() {
		return compiledProperty;
	}
	public void setCompiled(boolean value) {
		compiledProperty.set(value);
	}
	public boolean isCompiled() {
		return compiledProperty.get();
	}

	// To GUI
	/* public <T extends Node> T toGUI(Class clazz) {
		if (clazz instanceof BotItem) {
			return (T) new BotItem(this);
		}
		if (clazz instanceof DebugPane) {
			return (T) new DebugPane(this);
		}
	} */
}