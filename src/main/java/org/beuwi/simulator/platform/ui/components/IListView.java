package org.beuwi.simulator.platform.ui.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.List;

public class IListView<T> extends ListView<T>
{
	public IListView()
	{
		this.setContextMenu(new IContextMenu
		(
			new IMenuItem("Select All", event -> this.getSelectionModel().selectAll())
		));
	}

	/* public void addMenuItem(IMenuItem)
	{
		this.getContextMenu()
	} */

	public void setContextMenu(IContextMenu menu)
	{
		menu.setNode(this);
	}

	public void addItem(T item)
	{
		this.getItems().add(item);
	}

	public void addItems(List list)
    {
		this.getItems().addAll(list);
    }

    /* public void setItem(Node item)
    {
        getItems().set(item);
    } */

    public void setItems(List list)
    {
		this.getItems().setAll(list);
    }

    public ObservableList<T> getSelectedItems()
	{
		return this.getSelectionModel().getSelectedItems();
	}
}
