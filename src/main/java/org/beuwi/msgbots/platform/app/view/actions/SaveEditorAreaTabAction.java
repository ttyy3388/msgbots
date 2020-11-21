package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Node;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

public class SaveEditorAreaTabAction implements Action
{
	private static TabView control;

	@Override
	public void init()
	{
		control = MainAreaPart.getComponent();
	}

	public static void execute(String name)
	{
		execute(control.getTab(name));
	}

	public static void execute(Tab tab)
	{
		if (tab != null)
		{
			Node content = tab.getContent();

			if (content instanceof Editor)
			{
				Editor editor = (Editor) content;

				FileManager.save(FileManager.getBotScript(tab.getText()), editor.getText());
			}
		}
		else
		{
			return ;
		}
	}

	@Override
	public String getName()
	{
		return "save.editor.area.tab.action";
	}
}
