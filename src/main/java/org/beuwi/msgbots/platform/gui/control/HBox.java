package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class HBox<T> extends javafx.scene.layout.HBox
{
	public static final String DEFAULT_STYlE_CLASS = "hbox";

	private int index = 0;

	public HBox()
	{
		this(null);
	}

	public HBox(Node... items)
	{
		/* getChildren().addListener((ListChangeListener) change ->
		{
			while (change.next())
			{
				if (change.wasAdded())
				{
				    Node item = getItems().get(index);

					if (item instanceof Region)
                    {
                        ((Region) item).setMinHeight(getMinHeight());
                        ((Region) item).setPrefHeight(getPrefHeight());
                    }

					index ++;
				}
			}
		}); */

		if (items != null)
		{
			addItem(items);
		}

		getHeightProperty().addListener(change ->
        {
            for (Node item : getItems())
            {
                if (item instanceof Region)
                {
                    // ((Region) item).setMinHeight(getHeight());
                    ((Region) item).setPrefHeight(getHeight());
                }
            }
        });

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

	public void add(Node... items)
	{
		getItems().addAll(items);
	}

	public void addItem(Node... items)
	{
		getItems().addAll(items);
	}

	public Node get(int index)
	{
		return getItems().get(index);
	}

	public Node getItem(int index)
	{
		return getItems().get(index);
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