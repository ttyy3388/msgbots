package org.beuwi.msgbots.platform.gui.layout;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class GridPanel extends GridPane
{
    private static final String DEFAULT_STYLE_CLASS = "grid-panel";

    public void setItems(Node... items)
    {
        getChildren().setAll(items);
    }

    public void addItem(Node item, int column, int row)
    {
        add(item, column, row);
    }

    public void addColumn()
    {
        getColumnConstraints().add(new ColumnConstraints());
    }

    public void addColumn(int width)
    {
        getColumnConstraints().add(new ColumnConstraints(width));
    }

    public void setDefaultColumn(int... count)
    {
        for (int column : count)
        {
            // addColumn(column);

            if (column > 0)
            {
                addColumn(column);
            }
            else
            {
                addColumn();
            }
        }
    }

    public ObservableList<Node> getItems()
    {
        return getChildren();
    }
}