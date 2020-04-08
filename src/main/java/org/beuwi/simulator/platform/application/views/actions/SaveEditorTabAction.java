package org.beuwi.simulator.platform.application.views.actions;

import org.beuwi.simulator.managers.FileManager;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;
import org.beuwi.simulator.platform.ui.components.ICodeArea;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class SaveEditorTabAction
{
	private static ITabPane pane;

	public static void initialize()
	{
		pane = EditorAreaPart.getComponent();
	}

	// 현재 열린 탭 저장
	public static void update()
	{

	}

	// 해당 탭 저장
	public static void update(String name)
	{
		String id = "@script::" + name;

		if (pane.getTabItem(id) != null)
		{
			FileManager.save(FileManager.getBotScript(name), ((ICodeArea) pane.getTabItem(id).getContent()).getText());
		}
	}
}