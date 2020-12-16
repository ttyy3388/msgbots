package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.Collection;

// @DefaultProperty("items")
public class VBox<T> extends javafx.scene.layout.VBox
{
	private static final String DEFAULT_STYLE_CLASS = "vbox";

	private final BooleanProperty fittable = new SimpleBooleanProperty();

	private final InvalidationListener listener = new InvalidationListener()
	{
		@Override
		public void invalidated(Observable observable)
		{
			for (Node item : getItems())
			{
				if (item instanceof Region)
				{
					((Region) item).setPrefWidth(getWidth());
				}
			}
		}
	};

	public VBox()
	{
		this(null);
	}

	public VBox(Node... items)
	{
		if (items != null)
		{
			addItems(items);
		}

		getFittableProperty().addListener(event ->
		{
			if (isFittable())
			{
				getWidthProperty().addListener(listener);
			}
			else
			{
				getWidthProperty().removeListener(listener);
			}
		});

		// setOnMouseClicked();
		
		addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void clear()
	{
		getItems().clear();
	}

	public int find(String id)
	{
		for (int index = 0 ; index < getItems().size() ; index ++)
		{
			if (getItem(index).getId().equals(id))
			{
				return index;
			}
		}

		return -1;
	}

	public void delete(T item)
	{
		getItems().remove(item);
	}

	public void remove(T item)
	{
		getItems().remove(item);
	}

	public void addItem(Node item)
	{
		getItems().add(item);
	}

	public void addItem(int index, Node item)
	{
		getItems().add(index, item);
	}

	public void addItems(Node... items)
	{
		getItems().addAll(items);
	}

	public void addItems(ObservableList list)
	{
		getChildren().addAll(list);
	}

	public void setItem(Node item)
	{
		getItems().setAll(item);
	}

	public void setItem(int index, Node item)
	{
		getItems().set(index, item);
	}

	public void setItems(Node... items)
	{
		getItems().setAll(items);
	}

	public void setItems(Collection<? extends Node> collection)
	{
		getItems().setAll(collection);
	}

	public void setFittable(boolean fittable)
	{
		this.fittable.set(fittable);
	}

	public void addStyleClass(String style)
	{
		getStyleClass().add(style);
	}

	public boolean isFittable()
	{
		return fittable.get();
	}

	public boolean contains(Node item)
	{
		return getItems().contains(item);
	}

	public int getIndex(String id)
	{
		for (int index = 0 ; index < getItems().size() ; index ++)
		{
			if (getItem(index).getId().equals(id))
			{
				return index;
			}
		}

		return -1;
	}

	public Node getItem(int index)
	{
		return getItems().get(index);
	}

	public Node getItem(String id)
	{
		return getItems().get(getIndex(id));
	}

	public ObservableList<Node> getItems()
	{
		return getChildren();
	}

	public ObservableList<Node> getItemsProperty()
	{
		return getItems();
	}

	public ReadOnlyDoubleProperty getWidthProperty()
	{
		return widthProperty();
	}

	public ReadOnlyDoubleProperty getHeightProperty()
	{
		return heightProperty();
	}

	public BooleanProperty getFittableProperty()
	{
		return fittable;
	}
}