package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseButton;
import org.beuwi.simulator.platform.application.views.actions.CloseTabAction;

import java.util.List;

// Draggable Tab Pane
public class ITabPane extends TabPane
{
	public ITabPane()
	{
		this.setTabClosable(false);
		this.setTabMinHeight(24);
		this.setTabMaxHeight(30);
		this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
	}

	public void setTabClosable(boolean closable)
	{
		if (closable)
		{
			List<Tab> list = this.getTabs();

			for (Tab tab : list)
			{
				ITab itab = (ITab) tab;

                itab.getHeader().setOnMouseClicked(event ->
                {
                    if (MouseButton.MIDDLE.equals(event.getButton()))
                    {
                        CloseTabAction.update(itab);
                    }
                });

				itab.getCloseButton().setOnAction(event ->
				{
					CloseTabAction.update(itab);
				});

				itab.getCloseButton().setOnMouseClicked(event ->
                {
                    if (MouseButton.PRIMARY.equals(event.getButton()) || MouseButton.MIDDLE.equals(event.getButton()))
                    {
                        CloseTabAction.update(itab);
                    }
                });
			}
		}
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