package org.beuwi.msgbots.setting;

import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.base.Project;

public class ProjectSettings {
	public static ProjectSetting get(String name) {
		Project project = ProjectManager.findByName(name);
		if (project == null) {
			throw new NullPointerException();
		}
		return new ProjectSetting(project);
	}
	public static ProjectSetting get(Project project) {
        return new ProjectSetting(project);
    }

	public static class ProjectSetting {

		private final Project project;
		private final JObject json;

		private ProjectSetting(Project project) {
			this.project = project;
			this.json = project.getSetting();
		}

		// 현재 없는 키를 입력할 경우는 생각하지 않음
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

			FileManager.write(project.getFile("bot.json"), json.toString());
		}
	}
}