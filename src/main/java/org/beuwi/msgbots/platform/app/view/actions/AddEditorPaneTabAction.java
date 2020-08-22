package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.gui.area.EditorPane;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class AddEditorPaneTabAction implements Action
{
    private static EditorPane editor;

	@Override
	public void init()
	{
		editor = EditorAreaPart.getRoot();
	}

	public static void execute(Tab tab)
	{
		editor.getSelectedPane().addTab(tab);
	}

	@Override
    public String getName()
    {
        return "add.editor.tab.action";
    }
}