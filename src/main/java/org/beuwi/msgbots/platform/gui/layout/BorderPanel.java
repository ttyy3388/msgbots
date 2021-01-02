package org.beuwi.msgbots.platform.gui.layout;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

// @DefaultProperty("center")
public class BorderPanel extends BorderPane
{
    private static final String DEFAULT_STYLE_CLASS = "border-panel";

    public void setPadding(double padding) {
        super.setPadding(new Insets(padding));
    }

    /* public ObservableList<Node> getItems() {
        return getChildren();
    } */
}