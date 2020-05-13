package org.beuwi.simulator.platform.ui.components;

import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.ui.skins.ITabPaneSkin;

import java.util.List;
import java.util.stream.Collectors;

public class ITabPane extends TabPane
{
	{
		getStyleClass().add("ifx-tab-pane");
	}

	private final ITabPaneSkin skin = new ITabPaneSkin(this);

	public ITabPane()
	{
		this(null);
	}

	public ITabPane(ITab... tabs)
	{
		setSkin(skin);
		setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

		if (tabs != null)
		{
			getTabs().addAll(tabs);
		}
	}

	public void addTab(ITab tab)
	{
		getTabs().add(tab);
		selectTab(tab);
	}

	public void closeTab(Tab tab)
	{
		removeTab(tab);
	}

	public void removeTab(Tab tab)
	{
		getTabs().remove(tab);
	}

	public void selectTab(Tab tab, Side side)
	{
		List<Tab> tabs = getTabs();

		int index = tabs.indexOf(tab);

		switch (side)
		{
			// Next
			case RIGHT:

				if (index < tabs.size() - 1)
				{
					selectTab(index + 1);
				}
				else
				{
					selectTab(0);
				}

				break;

			// Previous
			case LEFT:

				if (index != 0)
				{
					selectTab(index - 1);
				}
				else
				{
					selectTab(tabs.size() - 1);
				}

				break;
		}
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

	public void setTabWidth(double width)
	{
		setTabMinWidth(width);
		setTabMaxWidth(width);
	}

	public void setTabHeight(double height)
	{
		setTabMinHeight(height);
		setTabMaxHeight(height);
	}

	public void setTabSize(double width, double height)
	{
		setTabMinWidth(width);
		setTabMinHeight(height);
		setTabMaxHeight(height);
	}

	public void setButtonBar(HBox hbox)
	{
		skin.setButtonBar(hbox);
	}

	public List<ITab> getTabList()
	{
		return getTabs().stream().map(tab -> (ITab) tab).collect(Collectors.toList());
	}

	public ITab getTabItem(int index)
	{
		return getTabList().get(index);
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