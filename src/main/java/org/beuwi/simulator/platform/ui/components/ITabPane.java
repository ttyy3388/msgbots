package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ITabPane extends TabPane
{
	public ITabPane()
	{
		// super();
	}

	public void addTab(Tab tab)
	{
		getTabs().add(tab);
		selectTab(tab);
	}

	public void selectTab(String id)
	{
		selectTab(getTabItem(id));
	}

	public void selectTab(Tab itab)
	{
		getSelectionModel().select(itab);
	}

	public boolean tabExists(String id)
	{
		return getTabIndex(id) != -1;
	}

	public Tab getTabItem(String id)
	{
		return getTabIndex(id) != -1 ? getTabs().get(getTabIndex(id)) : null;
	}

	public int getTabIndex(String id)
	{
		for (int index = 0 ; index < getTabs().size() ; index ++)
		{
			if (getTabs().get(index).getId().equals(id))
			{
				return index;
			}
		}
		return -1;
	}
}