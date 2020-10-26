package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;

@DefaultProperty("items")
public class AnchorPane extends javafx.scene.layout.AnchorPane
{
    public AnchorPane()
    {
        this(null);
    }

    public AnchorPane(Node content)
    {
        if (content != null)
        {
            addItem(content);
        }
    }

    public void addItem(Node... items)
    {
        getItems().setAll(items);
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
