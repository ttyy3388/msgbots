package org.beuwi.msgbots.program.gui.layout;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;

public class GridPane extends javafx.scene.layout.GridPane {
    {
        getStyleClass().add("grid-pane");
    }

    public GridPane() {
        this(0, 0);
    }

    public GridPane(int column, int row) {
    }

    public void addItem(Node item, int column, int row) {
        add(item, column, row);
    }
    public void addItem(Node item, int column, int row, int colspan, int rowspan) {
        add(item, column, row, colspan, rowspan);
    }

    public void addColumn() {
        getColumnConstraints().add(new ColumnConstraints());
    }

    public void addColumn(int width) {
        getColumnConstraints().add(new ColumnConstraints(width));
    }

    public void setDefaultColumn(int... count) {
        for (int column : count) {
            if (column != 0) {
                addColumn(column);
            }
            else {
                addColumn();
            }
        }
    }
}