package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;

import java.util.ArrayList;

public class CloseOtherEditorTabsAction
{
	// 현재 탭 닫기
	public static void update(Tab target)
	{
		ArrayList<Tab> list = new ArrayList<>();

		for (Tab tab : EditorAreaPart.getSelectedPane().getTabs())
		{
			if (tab != target)
			{
				list.add(tab);
			}
		}

		CloseEditorTabAction.update(list);
	}
}