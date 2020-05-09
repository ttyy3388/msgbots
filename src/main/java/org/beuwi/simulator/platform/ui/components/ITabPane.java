package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;
import java.util.stream.Collectors;

public class ITabPane extends TabPane
{
	{
		getStyleClass().add("ifx-tab-pane");
	}

	public ITabPane()
	{
		setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
	}

	public void addTab(ITab tab)
	{
		getTabs().add(tab);
	}

	public void closeTab(Tab tab)
	{
		removeTab(tab);
	}

	public void removeTab(Tab tab)
	{
		getTabs().remove(tab);
	}

	public void selectTab(String id)
	{
		selectTab(getTabItem(id));
	}

	public void selectTab(int index)
	{
		getSelectionModel().select(index);
	}

	public void selectTab(Tab tab)
	{
		getSelectionModel().select(tab);
	}

	public List<ITab> getITabs()
	{
		return getTabs().stream().map(tab -> (ITab) tab).collect(Collectors.toList());
	}

	public ITab getTabItem(int index)
	{
		return getITabs().get(index);
	}

	public ITab getTabItem(String id)
	{
		int index = getTabIndex(id);

		return index != -1 ? getTabItem(index) : null;
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

	public ITab getSelectedTab()
	{
		return (ITab) getSelectionModel().getSelectedItem();
	}

	public int getSelectedIndex()
	{
		return getSelectionModel().getSelectedIndex();
	}

	public boolean isTabExists(String id)
	{
		return getTabIndex(id) != -1;
	}
}