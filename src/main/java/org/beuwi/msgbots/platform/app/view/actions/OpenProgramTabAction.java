package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;

public class OpenProgramTabAction implements Action {
	private static TabView control;

	@Override
	public void init() {
		control = MainAreaPart.getComponent();
	}

	public static void execute(TabItem tab) {
		execute(control, tab);
	}

	public static void execute(TabView control, TabItem tab) {
		control.addTab(tab);
	}

	@Override
	public String getName() {
		return "open.program.tab.action";
	}
}