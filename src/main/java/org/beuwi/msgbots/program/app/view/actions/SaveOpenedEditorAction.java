package org.beuwi.msgbots.program.app.view.actions;

import javafx.scene.Node;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.program.gui.control.TabItem;
import org.beuwi.msgbots.program.gui.control.TabView;
import org.beuwi.msgbots.program.gui.editor.Editor;
import org.beuwi.msgbots.program.gui.layout.DebugPane;

import java.io.File;

public class SaveOpenedEditorAction implements Executor {
	private static SaveOpenedEditorAction instance = null;

	private final TabView control = MainAreaPart.getInstance().getTabView();

	public void execute() {
		final TabItem item = control.getSelectedTab();
		if (item != null) {
			Node content = item.getContent();
			if (content instanceof DebugPane) {
				DebugPane pane = (DebugPane) content;
				Editor editor = pane.getEditor();
                File file = editor.getFile();
				String text = editor.getText();
				if (file != null && text != null) {
                    FileManager.write(file, text);
                }
			}
		}
	}
	public static SaveOpenedEditorAction getInstance() {
		if (instance == null) {
			instance = new SaveOpenedEditorAction();
		}
		return instance;
	}
}
