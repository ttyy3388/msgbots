package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

// Draggable Tab Pane
public class ITabPane extends TabPane
{
	{
		this.getStyleClass().add("ifx-tab-pane");
	}

	public ITabPane()
	{
		this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
	}

	public void addTab(ITab tab)
	{
		this.getTabs().add(tab);
		this.selectTab(tab);
	}

	public void closeTab(Tab tab)
	{
		this.removeTab(tab);
	}

	public void removeTab(Tab tab)
	{
		this.getTabs().remove(tab);
	}

	public void selectTab(int index)
	{
		this.getSelectionModel().select(index);
	}

	public void selectTab(String id)
	{
		this.selectTab(getTabItem(id));
	}

	public void selectTab(Tab tab)
	{
		this.getSelectionModel().select(tab);
	}

	public boolean tabExists(String id)
	{
		return this.getTabIndex(id) != -1;
	}

	public ITab getTab(int index)
	{
		return (ITab) this.getTabs().get(index);
	}

	public ITab getSelectedTab()
	{
		return (ITab) this.getSelectionModel().getSelectedItem();
	}

	public int getSelectedIndex()
	{
		return this.getSelectionModel().getSelectedIndex();
	}

	public Tab getTabItem(String id)
	{
		return this.getTabIndex(id) != -1 ? this.getTabs().get(getTabIndex(id)) : null;
	}

	public int getTabIndex(String id)
	{
		for (int index = 0 ; index < this.getTabs().size() ; index ++)
		{
			if (this.getTabs().get(index).getId().equals(id))
			{
				return index;
			}
		}

		return -1;
	}
}