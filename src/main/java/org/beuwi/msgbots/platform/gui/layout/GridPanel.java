package org.beuwi.msgbots.platform.gui.layout;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class GridPanel extends GridPane {
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
}