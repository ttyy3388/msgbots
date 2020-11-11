package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;

public abstract class SkinBase<C extends Control> extends javafx.scene.control.SkinBase<C>
{
    public SkinBase(final C control)
    {
        super(control);
    }

    public void clear()
    {
        getItems().clear();
    }

    public void delete(C item)
    {
        getItems().remove(item);
    }

    public void remove(C item)
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
}
