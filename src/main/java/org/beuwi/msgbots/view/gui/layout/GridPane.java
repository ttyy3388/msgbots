package org.beuwi.msgbots.view.gui.layout;

import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;

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

    public void addRow() {
        getRowConstraints().add(new RowConstraints());
    }

    public void addRow(double height) {
        getRowConstraints().add(new RowConstraints(height));
    }

    public void addColumn() {
        getColumnConstraints().add(new ColumnConstraints());
    }

    public void addColumn(double width) {
        getColumnConstraints().add(new ColumnConstraints(width));
    }

    public void initColumn(int... widths) {
        for (int column : widths) {
            if (column != 0) {
                addColumn(column);
            }
            else {
                addColumn();
            }
        }
    }

    public void initRow(int... heights) {
        for (int column : heights) {
            if (column != 0) {
                addRow(column);
            }
            else {
                addRow();
            }
        }
    }
}