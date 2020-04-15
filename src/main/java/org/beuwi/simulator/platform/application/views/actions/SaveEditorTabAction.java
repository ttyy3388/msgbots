package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;

public class SaveEditorTabAction
{
	// 현재 탭 저장
	public static void update()
	{
		// update(pane.getSelectedTab());
	}

	// 해당 탭 저장
	public static void update(String name)
	{
		// update(pane.getTabItem("@script::" + name));
	}

	public static void update(Tab tab)
	{
		// ID 부분 추후 수정

		/* if (tab != null)
		{
			// FileManager.save(FileManager.getBotScript(ProgramUtils.getName(tab.getId())), ((ICodeArea) tab.getContent()).getText());
		} */
	}
}