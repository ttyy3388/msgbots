package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.BotItem;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.editor.Editor;
import org.beuwi.msgbots.platform.gui.layout.ScriptPane;

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
		if (target != null) {
			control.selectTab(target);
		}
		else {
			AddMainAreaTabAction.execute(new TabItem(name, new ScriptPane(item.getFile())));
		}
	}

	@Override
	public String getName() {
		return "open.script.tab.action";
	}
}