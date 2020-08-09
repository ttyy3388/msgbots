package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;

public class AddEditorTabAction implements Action
{
    private static TabPane pane;

	@Override
	public void init()
	{
		pane = EditorAreaPart.getComponent();
	}

	public static void execute(String title, Node content)
	{
		pane.getTabs().add(new Tab(title, content));
	}

	@Override
    public String getName()
    {
        return "add.editor.tab.action";
    }
}