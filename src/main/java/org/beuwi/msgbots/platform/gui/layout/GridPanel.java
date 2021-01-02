package org.beuwi.msgbots.platform.gui.layout;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GridPanel extends GridPane
{
    private static final String DEFAULT_STYLE_CLASS = "grid-panel";

    public GridPanel() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    public void addItem(Node item, int column, int row) {
        add(item, column, row);
    }

    public void addColumn() {
        getColumnConstraints().add(new ColumnConstraints());
    }
    public void addColumn(int width) {
        getColumnConstraints().add(new ColumnConstraints(width));
    }

    public void addRow() {
        getRowConstraints().add(new RowConstraints());
    }
    public void addRow(int height) {
        getRowConstraints().add(new RowConstraints(height));
    }

    public void setDefaultRow(int... count) {
        for (int row : count) {
            if (row > 0) {
                addRow(row);
            }
            else {
                addRow();
            }
        }

    }

    public void setDefaultColumn(int... count) {
        for (int column : count) {
            if (column > 0) {
                addColumn(column);
            }
            else {
                addColumn();
            }
        }
    }

    /* public ObservableList<Node> getItems() {
        return getChildren();
    } */
}