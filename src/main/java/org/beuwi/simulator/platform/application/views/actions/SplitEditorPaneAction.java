package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.SplitPane;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.editor.IEditorPane;

public class SplitEditorPaneAction
{
	private static SplitPane pane;

	public static void initialize()
	{
		pane = EditorAreaPart.getComponent();
	}

	// 현재는 가로만 지원
	public static void update()
	{
		pane.getItems().add(new IEditorPane());
	}
}