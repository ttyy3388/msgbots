package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.EditorAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

import java.io.File;

public class OpenScriptTabAction implements Action
{
	private static TabView control;

	@Override
	public void init()
	{
		control = EditorAreaPart.getComponent();
	}

	public static void execute(String name)
	{
		if (control.contains(name))
		{
			control.select(name);
		}
		else
		{
			File file = FileManager.getBotScript(name);

			AddEditorAreaTabAction.execute(new Tab(name, new Editor(file)));
		}
	}

	@Override
	public String getName()
	{
		return "open.script.tab.action";
	}
}