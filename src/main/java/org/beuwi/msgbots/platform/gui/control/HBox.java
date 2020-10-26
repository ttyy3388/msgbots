package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class HBox<T> extends javafx.scene.layout.HBox
{
	private static final String DEFAULT_STYlE_CLASS = "hbox";

	private final BooleanProperty fittable = new SimpleBooleanProperty();

	public HBox()
	{
		this(null);
	}

	public HBox(Node... items)
	{
		if (items != null)
		{
			addItem(items);
		}

		getStyleClass().add(DEFAULT_STYlE_CLASS);
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

	public void addItem(Node... items)
	{
		getItems().addAll(items);
	}

	public void setFittable(boolean fittable)
	{
		this.fittable.set(fittable);
	}

	private void setFitContent(boolean resize)
	{
		if (resize)
		{
			getHeightProperty().addListener(change ->
			{
				for (Node item : getItems())
				{
					if (item instanceof Region)
					{
						((Region) item).setPrefHeight(getHeight());
					}
				}
			});
		}
		else
		{
			return ;
		}
	}

	public boolean isFittable()
	{
		return fittable.get();
	}

	public Node getItem(int index)
	{
		return getItems().get(index);
	}

	public Node getLastItem()
	{
		return getItems().get(getItems().size());
	}

	public ObservableList<Node> getItems()
	{
		return getChildren();
	}

	public ReadOnlyDoubleProperty getWidthProperty()
	{
		return widthProperty();
	}

	public ReadOnlyDoubleProperty getHeightProperty()
	{
		return heightProperty();
	}
}