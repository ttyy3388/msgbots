package org.beuwi.msgbots.manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.openapi.Project;

import java.util.ArrayList;
import java.util.List;

// 모든 봇은 봇 매니저에 등록 돼 있어야 관리됨 : 즉 총괄 사무실 개념
// 봇 추가는 봇 목록이 초기화 될 때만 추가됨 : [RefreshBotListAction] 참고
// 프로젝트는 이름 또한 유일 키로 간주하므로 이름으로도 관리함
public class ProjectManager implements Manager {
	private static final ObservableList<Project> list = FXCollections.observableArrayList();

	public static void register(Project project) {
		List<String> names = getNames();
		// 이름 또한 유일 키 이므로, 이름으로 검사함
		if (names.contains(project.getName())) {
			throw new RuntimeException("project already exists in a list");
		}
		else {
			list.add(project);
		}
	}

	public static boolean exists(String name) {
		return findByName(name) != null;
	}

	public static Project findByName(String name) {
		for (int index = 0 ; index < list.size() ; index ++) {
			Project project = list.get(index);
			if (name.equals(project.getName())) {
				return project;
			}
		}
		return null;
	}

	public static ObservableList<Project> getList() {
		return list;
	}

	public static List<String> getNames() {
		List<String> names = new ArrayList();
		list.forEach(bot -> {
			names.add(bot.getName());
		});
		return names;
	}

	/* public static void setPower(String name, boolean power) {
		BotListTab.getInstance().getBotView().findById(name).setPower(power);
	}
	public static void setCompiled(String name, boolean compiled) {
		BotListTab.getInstance().getBotView().findById(name).setCompiled(compiled);
	}
	public static boolean getPower(String name) {
		return BotListTab.getInstance().getBotView().findById(name).getPower();
	}
	public static boolean isCompiled(String name) {
		return BotListTab.getInstance().getBotView().findById(name).isCompiled();
	} */
}
