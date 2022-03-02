package org.beuwi.msgbots.view.app.actions;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.view.util.GUIManager;
import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.app.parts.MainAreaPart;
import org.beuwi.msgbots.view.gui.control.TabItem;
import org.beuwi.msgbots.view.gui.control.TabView;

public class OpenDebugTabAction implements Executor {
	private static OpenDebugTabAction instance = null;

	private final TabView tabView = MainAreaPart.getInstance().getTabView();

	public void execute(Project project) {
		String botName = project.getName();
		TabItem tabItem = tabView.getTab(botName);

		// 이미 해당 탭이 추가 돼 있다면 선택
		if (tabItem != null) {
		    tabView.selectTab(tabItem);
		}
		else {
			tabItem = new TabItem();
			tabItem.setText(botName);
			tabItem.setContent(GUIManager.toViewPane(project));
			tabView.addTab(tabItem);
		}
	}

	public static OpenDebugTabAction getInstance() {
		if (instance == null) {
			instance = new OpenDebugTabAction();
		}
		return instance;
	}
}
