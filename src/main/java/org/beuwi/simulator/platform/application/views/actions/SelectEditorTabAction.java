package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.beuwi.simulator.platform.ui.components.IPos;

import java.util.List;

public class SelectEditorTabAction
{
	public static void update(Tab tab, IPos pos)
	{
		TabPane pane = tab.getTabPane();

		List<Tab> tabs = pane.getTabs();

        int index = tabs.indexOf(tab);

		switch (pos)
		{
			// Next
			case RIGHT:

			    if (index < tabs.size())
                {
                    pane.getSelectionModel().select(index + 1);
                }

				break;

			// Previous
			case LEFT:

			    if (index != 0)
                {
                    pane.getSelectionModel().select(index - 1);
                }

				break;
		}
	}
}