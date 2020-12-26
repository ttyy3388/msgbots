package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

import java.io.File;

public class OpenScriptTabAction implements Action {
	private static TabView control;

	@Override
	public void init() {
		control = MainAreaPart.getComponent();
	}

	public static void execute(String name) {
		if (control.containsTab(name)) {
			control.selectTab(name);
		}
		else {
			File file = FileManager.getBotScript(name);

			AddMainAreaTabAction.execute(new Tab(name, new Editor(file)));
		}
	}

	@Override
	public String getName() {
		return "open.script.tab.action";
	}
}