package org.beuwi.simulator.platform.ui.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;

public class IListView<T> extends ListView<T>
{
	{
		getStyleClass().add("ifx-list-view");
	}

	public IListView()
	{
		setContextMenu(new IContextMenu
		(
			new IMenuItem("Select All", event -> this.getSelectionModel().selectAll())
		));
	}

	public void clear()
	{
		getItems().clear();
	}

	public void scrollToLast()
	{
		scrollTo(getItems().size());
	}

	public void scrollToFirst()
	{
		scrollTo(0);
	}

	public void setContextMenu(IContextMenu menu)
	{
		menu.setNode(this);
	}

	public void addItem(T item)
	{
		getItems().add(item);
	}

	public void addItems(List list)
    {
		getItems().addAll(list);
    }

    public void setItems(List list)
    {
		getItems().setAll(list);
    }

    public ObservableList<T> getSelectedItems()
	{
		return getSelectionModel().getSelectedItems();
	}
}
