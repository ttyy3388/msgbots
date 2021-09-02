package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;

public class AddMainAreaTabAction implements Executor {
	private static AddMainAreaTabAction instance = null;

	private final TabView tabView = MainAreaPart.getInstance().getTabView();

	public void execute(TabItem tabItem) {
		tabView.addTab(tabItem);
	}

	public static AddMainAreaTabAction getInstance() {
		if (instance == null) {
			instance = new AddMainAreaTabAction();
		}
		return instance;
	}
}