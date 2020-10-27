package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

@DefaultProperty("items")
public class StackPane<T> extends javafx.scene.layout.StackPane
{
	public StackPane()
	{
		this(null);
	}

	public StackPane(Node content)
	{
	    if (content != null)
        {
            addItem(content);
        }
	}

	public Node getContent()
	{
		return getItems().get(0);
	}

	public void setContent(Node content)
	{
		getItems().setAll(content);
	}

	public void addItem(Node... items)
	{
		getItems().addAll(items);
	}

	public Node getItem(int index)
	{
		return getItems().get(index);
	}

	public ObservableList<Node> getItems()
	{
		return getChildren();
	}

	public ReadOnlyObjectProperty<Parent> getParentProperty()
	{
		return parentProperty();
	}
}