package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.TabItem;
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
		File file = FileManager.getBotScript(name);
		Editor editor = new Editor(file);
		AddMainAreaTabAction.execute(new TabItem(name, editor));
	}

	@Override
	public String getName() {
		return "open.script.tab.action";
	}
}