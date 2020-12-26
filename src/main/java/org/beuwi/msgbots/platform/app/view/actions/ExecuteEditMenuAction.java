package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Node;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

public class ExecuteEditMenuAction implements Action {
	private static TabView control;

	@Override
	public void init() {
		control = MainAreaPart.getComponent();
	}

	public static void execute(String message) {
		Tab target = control.getSelectedTab();

		if (target != null) {
			Node content = target.getContent();

			if (content instanceof Editor) {
				((Editor) content).execute(message);
			}
		}
	}

	@Override
	public String getName() {
		return "redo.tab.editor.action";
	}
}
