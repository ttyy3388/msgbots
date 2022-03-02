package org.beuwi.msgbots.view.app.actions;

import javafx.scene.Node;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.app.parts.MainAreaPart;
import org.beuwi.msgbots.view.gui.control.TabItem;
import org.beuwi.msgbots.view.gui.control.TabView;
import org.beuwi.msgbots.view.gui.editor.Editor;
import org.beuwi.msgbots.view.gui.layout.DebugPane;

public class TriggerOpenedEditorAction implements Executor {
	private static TriggerOpenedEditorAction instance = null;

	private final TabView control = MainAreaPart.getInstance().getTabView();

	public void execute(String trigger) {
		TabItem item = control.getSelectedTab();
		if (item != null) {
			Node content = item.getContent();
			if (content instanceof DebugPane pane) {
				Editor editor = pane.getEditor();
				switch (trigger) {
					case "undo" -> editor.undo();
					case "redo" -> editor.redo();
					case "cut" -> editor.cut();
					case "copy" -> editor.copy();
					case "paste" -> editor.paste();
				}
			}
		}
	}

	public static TriggerOpenedEditorAction getInstance() {
		if (instance == null) {
			instance = new TriggerOpenedEditorAction();
		}
		return instance;
	}
}
