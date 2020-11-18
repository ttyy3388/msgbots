package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;

// @DefaultProperty("items")
public class StackPanel extends javafx.scene.layout.StackPane
{
	private static final String DEFAULT_STYLE_CLASS = "stack-panel";

	public StackPanel()
	{
		this(null);
	}

	public StackPanel(Node content)
	{
	    if (content != null)
        {
            addItem(content);
        }
	}

	public void addItem(Node... item)
	{
		getItems().addAll(item);
	}

	public void setItem(Node... item)
	{
		getItems().setAll(item);
	}

	public void setPadding(double padding)
	{
		super.setPadding(new Insets(padding));
	}

	public void addStyleClass(String style)
	{
		getStyleClass().add(style);
	}

	public void setStyleClass(String style)
	{
		getStyleClass().setAll(style);
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