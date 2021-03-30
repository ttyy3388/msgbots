package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.TabItem;

public class OpenProgramTabAction implements Action {

	@Override
	public void init() {
	}

	public static void execute(TabItem tab) {
		AddMainAreaTabAction.execute(tab);
	}

	/* public static void execute(TabView control, TabItem tab) {
		control.addTab(tab);
	} */

	@Override
	public String getName() {
		return "open.program.tab.action";
	}
}