package org.beuwi.msgbots.program.app.view.actions;

import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.manager.ProjectManager;
import org.beuwi.msgbots.program.app.manager.GUIManager;
import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.program.app.view.tabs.BotListTab;
import org.beuwi.msgbots.program.gui.control.BotItem;
import org.beuwi.msgbots.program.gui.control.BotView;
import org.beuwi.msgbots.program.gui.control.TabView;
import org.beuwi.msgbots.utils.SharedValues;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RefreshAllBotsAction implements Executor {
	private static RefreshAllBotsAction instance = null;

	private final BotView botView = BotListTab.getInstance().getBotView();
	private final TabView tabView = MainAreaPart.getInstance().getTabView();

	public void execute() {
		// boolean status = MdbManager.getStatus();
		boolean status = false;
		if (status) {
			/* MdbManager.getBotList().forEach(bot -> {
				GUIManager.toListItem(bot);
			}); */
		}
		else {
			// 바로 비우지 않고, 저장할 리스트를 만드는 이유는 컴파일 여부 등 저장된 값을 보존하기 위해서임
			// 이렇게 안하면, 리프레시 할 때 마다 모든 봇의 컴파일, 파워 등 정보가 날라감
			List<Project> projects = new ArrayList<>();
			List<BotItem> items = new ArrayList<>();

			File[] folders = SharedValues.getFile("BOT_FOLDER_FILE").listFiles(File::isDirectory);
			// 속도를 위해 for-each가 아닌 for문으로 함
			int length = folders.length;
			for (int i = 0 ; i < length ; i ++) {
				File folder = folders[i];
				// 프로젝트인지 확인
				if (Project.check(folder)) {
					// 이미 프로젝트에 해당 아이템이 등록 돼 있다면
					Project project = ProjectManager.findByName(folder.getName());
					if (project != null) {
						projects.add(project);
					}
					// 새로 등록된 프로젝트라면
					else {
						// 새 프로젝트 아이템 생성
						project = new Project(folder);
						projects.add(project);
						GUIManager.registry(project); // GUIManager 등록
					}
					items.add(GUIManager.toListItem(project));
				}
			}

			// newList에는 없으나 프로젝트 목록에 있다면 삭제된 프로젝트로 간주
			// 즉 newList로 목록을 밀어버림
			ProjectManager.getList().setAll(projects);
			// GUIManager.getList().clear();

			// 새 목록으로 목록 재 지정: 기존 아이템을 남겨두고 정렬하는 게 비효율적이기 때문임
			botView.getItems().setAll(items);
		}
	}

	public static RefreshAllBotsAction getInstance() {
		if (instance == null) {
			instance = new RefreshAllBotsAction();
		}
		return instance;
	}
}
