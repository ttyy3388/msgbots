package org.beuwi.msgbots.program.app.view.actions;

import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.program.app.manager.GUIManager;
import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.program.gui.control.TabItem;
import org.beuwi.msgbots.program.gui.control.TabView;

public class OpenDebugTabAction implements Executor {
	private static OpenDebugTabAction instance = null;

	private final TabView tabView = MainAreaPart.getInstance().getTabView();

	public void execute(Project project) {
		String botName = project.getName();
		// 이미 해당 탭이 추가 돼 있다면 선택
		if (tabView.findTab(botName) != -1) {
		    tabView.selectTab(tabView.findTab(botName));
		}
		else {
			tabView.addTab(new TabItem(botName, GUIManager.toViewPane(project)));
		}
	}

	public static OpenDebugTabAction getInstance() {
		if (instance == null) {
			instance = new OpenDebugTabAction();
		}
		return instance;
	}
}
