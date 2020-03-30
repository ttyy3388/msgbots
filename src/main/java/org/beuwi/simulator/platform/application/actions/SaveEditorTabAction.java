package org.beuwi.simulator.platform.application.actions;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ICodeArea;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class SaveEditorTabAction
{
	private static ITabPane pane;

	public static void initialize()
	{
		pane = (ITabPane) EditorAreaPart.getNameSpace().get("tapEditorArea");
	}

	// name이 없으면 전체 저장
	/* public static void update()
	{
		if (pane.getTabs() != null)
		{
			for (Tab tab : pane.getTabs())
			{
				FileManager.save(FileManager.getBotScript(tab.getId()), ((ICodeArea) tab.getContent()).getText());
			}
		}
	} */

	public static void update(String name)
	{
		String id = "@script::" + name;

		if (pane.getTabItem(id) != null)
		{
			FileManager.save(FileManager.getBotScript(name), ((ICodeArea) pane.getTabItem(id).getContent()).getText());
		}
	}
}