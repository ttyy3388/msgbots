package org.beuwi.simulator.platform.ui.components;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import org.beuwi.simulator.platform.application.actions.CopyAction;

import java.util.ArrayList;
import java.util.List;

public class IListView<T> extends ListView<T>
{
	{
		getStyleClass().add("ifx-list-view");
	}

	public IListView()
	{
		IContextMenu menu = new IContextMenu
		(
			new IMenuItem("Select All", "Ctrl + A", event -> selectAll())
		);

		menu.setNode(this);
	}

	public void cut()
	{
		copy();
		delete();
	}

	public void copy()
	{
		ArrayList<String> list = new ArrayList<>();

		for (Object item : getSelectedItems())
		{
			if (item instanceof HBox)
			{
				list.add(((HBox) item).getId());
			}

			if (item instanceof AnchorPane)
			{
				list.add(((AnchorPane) item).getId());
			}
		}

		CopyAction.update(list);
	}

	public void delete()
	{
		getItems().removeAll(getSelectedItems());
	}

	public void selectAll()
	{
		getSelectionModel().selectAll();
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
