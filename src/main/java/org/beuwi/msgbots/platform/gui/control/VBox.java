package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

@DefaultProperty("items")
public class VBox<T> extends javafx.scene.layout.VBox
{
	public static final String DEFAULT_STYLE_CLASS = "vbox";

	private int index = 0;

	public VBox()
	{
		this(null);
	}

	public VBox(Node... items)
	{
		if (items != null)
		{
			addItem(items);
		}

		getStyleClass().add(DEFAULT_STYLE_CLASS);
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

	public void addItem(Node item)
	{
		getItems().add(item);
	}

	public void addItem(Node... items)
	{
		getItems().addAll(items);
	}

	public void addItem(ObservableList list)
	{
		getChildren().addAll(list);
	}

	public void setFitContent(boolean resize)
	{
		if (resize)
		{
			widthProperty().addListener(change ->
			{
				for (Node item : getItems())
				{
					if (item instanceof Region)
					{
						((Region) item).setPrefWidth(getWidth());
					}
				}
			});
		}
		else
		{
			return ;
		}
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