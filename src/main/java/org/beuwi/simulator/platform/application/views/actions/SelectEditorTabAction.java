package org.beuwi.simulator.platform.application.views.actions;

import javafx.scene.control.Tab;
import org.beuwi.simulator.platform.ui.components.IPos;
import org.beuwi.simulator.platform.ui.components.ITab;
import org.beuwi.simulator.platform.ui.components.ITabPane;

import java.util.List;

public class SelectEditorTabAction
{
	public static void update(ITabPane pane, IPos pos)
	{
		update(pane.getSelectedTab(), pos);
	}

	public static void update(ITab tab, IPos pos)
	{
		ITabPane pane = tab.getITabPane();

		List<Tab> tabs = pane.getTabs();

        int index = tabs.indexOf(tab);

		switch (pos)
		{
			// Next
			case RIGHT:

			    if (index < tabs.size() - 1)
                {
                    pane.selectTab(index + 1);
                }
			    else
				{
					pane.selectTab(0);
				}

				break;

			// Previous
			case LEFT:

			    if (index != 0)
                {
                    pane.selectTab(index - 1);
                }
			    else
				{
					pane.selectTab(tabs.size() - 1);
				}

				break;
		}
	}
}