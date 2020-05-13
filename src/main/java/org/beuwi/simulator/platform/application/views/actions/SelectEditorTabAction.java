package org.beuwi.simulator.platform.application.views.actions;

import javafx.geometry.Side;
import org.beuwi.simulator.platform.ui.components.ITab;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class SelectEditorTabAction
{
	public static void update(ITabPane pane, Side pos)
	{
		update(pane.getSelectedTab(), pos);
	}

	public static void update(ITab tab, Side pos)
	{
		ITabPane pane = tab.getITabPane();

		pane.selectTab(tab, pos);
	}
}