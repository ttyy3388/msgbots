package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Node;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;
import org.beuwi.msgbots.platform.gui.control.Bot;
import org.beuwi.msgbots.platform.gui.control.BotView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

import java.io.File;

public class RedoEditorTextAction implements Action {
	private static TabView control;

	@Override
	public void init() {
		control = MainAreaPart.getComponent();
	}

	public static void execute() {
		Tab target = control.getSelectedTab();

		if (target != null) {
			Node content = target.getContent();

			if (content instanceof Editor) {
				((Editor) content).action("redo");
			}
		}
	}

	@Override
	public String getName() {
		return "redo.tab.editor.action";
	}
}
