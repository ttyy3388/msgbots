package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.manager.GUIManager;
import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;

public class OpenDebugTabAction implements Executor {
	private static OpenDebugTabAction instance = null;

	private final TabView tabView = MainAreaPart.getInstance().getTabView();

	public void execute(Project project) {
		String botName = project.getName();
		// 이미 해당 탭이 추가 돼 있다면 선택
		if (tabView.findById(botName) != -1) {
		    tabView.selectTab(tabView.findById(botName));
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
