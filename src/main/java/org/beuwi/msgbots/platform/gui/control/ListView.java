package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

import java.util.Collection;
import java.util.List;

public class ListView<T> extends javafx.scene.control.ListView<T>
{
	private static final String DEFAULT_STYLE_CLASS = "list-view";

	public ListView()
	{
		// setStyleClass(DEFAULT_STYLE_CLASS);
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
		getItems().add(item); scroll(item);
	}

	public void addItems(T... items)
	{
		getItems().addAll(items);
	}

	public void addItems(Collection<? extends T> list)
	{
		getItems().addAll(list);
	}

	/* public void setItem(T... items)
	{
		getItems().setAll(items);
	} */

	public void setItems(T... items)
	{
		getItems().setAll(items);
	}

	public void setItems(Collection<? extends T> list)
	{
		getItems().setAll(list);
	}

	public void setContextMenu(ContextMenu menu)
	{
		menu.setNode(this);
	}

	public void addStyleClass(String style)
	{
		getStyleClass().add(style);
	}

	public void setStyleClass(String style)
	{
		getStyleClass().setAll(style);
	}

	public void setSelectionMode(SelectionMode mode)
	{
		getSelectionModel().setSelectionMode(mode);
	}

	public boolean contains(Node item)
	{
		return getItems().contains(item);
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

	public T getSelectedItem()
	{
		return getSelectionModel().getSelectedItem();
	}

	public ObservableList<T> getSelectedItems()
	{
		return getSelectionModel().getSelectedItems();
	}

	public ReadOnlyDoubleProperty getWidthProperty()
	{
		return widthProperty();
	}

	public DoubleProperty getMinWidthProperty()
	{
		return minWidthProperty();
	}

	public DoubleProperty getPrefWidthProperty()
	{
		return prefWidthProperty();
	}

	public DoubleProperty getMaxWidthProperty()
	{
		return maxWidthProperty();
	}
}
