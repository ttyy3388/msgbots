package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.BotItem;
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

	public static void execute(BotItem item) {
		String name = item.getName();
		TabItem target = control.getTab(name);
		// 이미 에디터 탭이 있으면 선택
		if (target == null) {
			File file = item.getFile();
			Editor editor = new Editor(file);
			AddMainAreaTabAction.execute(new TabItem(name, editor));
		}
		else {
			control.selectTab(target);
		}
	}

	@Override
	public String getName() {
		return "open.script.tab.action";
	}
}