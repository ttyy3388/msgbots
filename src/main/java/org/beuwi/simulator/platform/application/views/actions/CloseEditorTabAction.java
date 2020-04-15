package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import org.beuwi.simulator.platform.application.views.parts.EditorAreaPart;

import java.util.Collection;

public class CloseEditorTabAction
{
	// 현재 탭 닫기
	public static void update()
	{
		update(EditorAreaPart.getSelectedPane().getSelectedTab());
	}

	// 해당 탭 닫기
	public static void update(Tab tab)
	{
		EditorAreaPart.getSelectedPane().closeTab(tab);
	}

	public static void update(Collection tabs)
	{
		EditorAreaPart.getSelectedPane().getTabs().removeAll(tabs);
	}
}