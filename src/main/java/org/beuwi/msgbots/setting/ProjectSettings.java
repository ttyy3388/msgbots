package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.ProjectManager;

public class ProjectSettings {
	public static ProjectSettings getSetting(String name) {
		Project project = ProjectManager.findByName(name);
		if (project == null) {
			throw new NullPointerException();
		}
		return new ProjectSettings(project);
	}
	public static ProjectSettings getSetting(Project project) {
        return new ProjectSettings(project);
    }

    private final Project project;
    private final JObject json;

    private ProjectSettings(Project project) {
        this.project = project;
    	this.json = project.getSetting();
    }

	public <T> T getData(String address) {
		return (T) json.get(address);
	}

	public int getInt(String address) {
		return Integer.valueOf("" + getData(address));
	}

	public String getString(String address) {
		return String.valueOf("" + getData(address));
	}

	public boolean getBoolean(String address) {
		return Boolean.valueOf("" + getData(address));
	}

	public <T> void setData(String address, T value) {
		// 기본 타입이 아닐 경우
		if (!(value instanceof String ||
			  value instanceof Double ||
			  value instanceof Boolean ||
			  value instanceof Integer ||
			  value instanceof Long)) {
			json.put(address, value.toString());
		}
		else {
			json.put(address, value);
		}

		FileManager.write(project.getFile("log.json"), json.toString());
	}
}