package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

@DefaultProperty("items")
public class VBox<T> extends javafx.scene.layout.VBox
{
	private static final String DEFAULT_STYLE_CLASS = "vbox";

	private final BooleanProperty fittable = new SimpleBooleanProperty();

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

		getFittableProperty().addListener((observable) ->
		{
			setFitContent(isFittable());
		});

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

	public void addItem(int index, Node item)
	{
		getItems().add(index, item);
	}

	public void setFittable(boolean fittable)
	{
		this.fittable.set(fittable);
	}

	// 부모 높이에 자식 컴포넌트 높이를 강제로 맞춤
	private void setFitContent(boolean resize)
	{
		if (resize)
		{
			getWidthProperty().addListener(change ->
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

	public boolean isFittable()
	{
		return fittable.get();
	}

	public Node getItem(int index)
	{
		return getItems().get(index);
	}

	public Node getItem(String id)
	{
		return getItems().get(getIndex(id));
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