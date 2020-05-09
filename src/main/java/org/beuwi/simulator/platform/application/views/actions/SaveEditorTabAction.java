package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ICodeArea;
import org.beuwi.simulator.utils.ProgramUtils;

import java.util.List;

public class SaveEditorTabAction
{
	public static void update()
	{
		update(EditorAreaPart.getSelectedPane().getSelectedTab());
	}

	public static void update(String name)
	{


		// update(EditorAreaPart.getSelectedTabPane().getTabItem("@script::" + name));
	}

	public static void update(Tab tab)
	{


		FileManager.save(FileManager.getBotScript(ProgramUtils.getName(tab.getId())), ((ICodeArea) tab.getContent()).getText());


		if (tab != null)
		{
			FileManager.save(FileManager.getBotScript(ProgramUtils.getName(tab.getId())), ((ICodeArea) tab.getContent()).getText());
		}
	}

	public static void update(List<Tab> tabs)
	{

	}
}