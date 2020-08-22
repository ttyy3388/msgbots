package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.gui.area.EditorPane;
import org.beuwi.msgbots.platform.gui.control.SplitPos;

public class SplitEditorPaneAction implements Action
{
	private static EditorPane editor;

	@Override
	public void init()
	{
		editor = EditorAreaPart.getRoot();
	}

	public static void execute(SplitPos pos)
	{
		editor.split(pos, editor.getSelectedPane());
	}

	@Override
	public String getName() {
		return null;
	}
}
