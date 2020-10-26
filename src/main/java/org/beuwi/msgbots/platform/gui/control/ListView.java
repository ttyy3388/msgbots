package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

import java.util.List;

public class ListView<T> extends javafx.scene.control.ListView<T>
{
	public ListView()
	{
		
	}

	public void scroll(T item)
	{
		scrollTo(item);
	}

	public void clear()
	{
		getItems().clear();
	}

	public void delete(T item)
	{
		getItems().remove(item);
	}

	public void remove(T item)
	{
		getItems().remove(item);
	}

	public void select()
	{
		getSelectionModel().selectAll();
	}

	public void select(T item)
	{
		getSelectionModel().select(item);
	}

	public void addItem(T item)
	{
		getItems().add(item);
		scroll(item);
	}

	public T getItem(int index)
	{
		return getItems().get(index);
	}

	public T getItem(String id)
	{
		List<T> list = getItems();

		for (T item : list)
		{
			if (item instanceof Node)
			{
				if (((Node) item).getId().equals(id))
				{
					return item;
				}
			}
		}

		return null;
	}

	public ObservableList<T> getSelectedItems()
	{
		return getSelectionModel().getSelectedItems();
	}

	public void setContextMenu(ContextMenu menu)
	{
		menu.setNode(this);
	}

	public void setSelectionMode(SelectionMode mode)
	{
		getSelectionModel().setSelectionMode(mode);
	}
}
