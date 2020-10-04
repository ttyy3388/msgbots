package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;

import java.util.List;

public class ListView<T> extends ScrollPane
{
	private final javafx.scene.control.ListView<T> root = new javafx.scene.control.ListView<T>();

	public ListView()
	{
		/* setCellFactory(param -> new ListCell<>()
		{
			{
				prefWidthProperty().bind(this.widthProperty());
				setMaxWidth(Control.USE_PREF_SIZE);
			}

			@Override
			protected void updateItem(T item, boolean empty)
			{
				super.updateItem(item, empty);

				if (item != null && !empty)
				{
					setGraphic(item);
				}
				else
				{
					setGraphic(null);
				}
			}
		}); */

		setContent(root);
		setFitToWidth(true);
		setFitToHeight(true);
	}

	public void scroll(T item)
	{
		root.scrollTo(item);
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

	public ObservableList<T> getItems()
	{
		return root.getItems();
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

	public MultipleSelectionModel<T> getSelectionModel()
	{
		return root.getSelectionModel();
	}

	public void setContextMenu(ContextMenu menu)
	{
		menu.setNode(this);
	}

	public void setSelectionMode(SelectionMode mode)
	{
		getSelectionModel().setSelectionMode(mode);
	}

	public void setCellFactory(Callback<javafx.scene.control.ListView<T>, ListCell<T>> value)
	{
		root.setCellFactory(value);
	}
}
