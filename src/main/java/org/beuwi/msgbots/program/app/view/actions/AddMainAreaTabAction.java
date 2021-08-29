package org.beuwi.msgbots.program.app.view.actions;

import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.program.gui.control.TabItem;
import org.beuwi.msgbots.program.gui.control.TabView;

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