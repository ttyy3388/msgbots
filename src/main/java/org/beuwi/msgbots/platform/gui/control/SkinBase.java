package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;

public class SkinBase<C extends Control> extends javafx.scene.control.SkinBase<C> {
    public SkinBase(C control) {
        super(control);
    }

    public ObservableList<Node> getItems() {
        return getChildren();
    }
}
