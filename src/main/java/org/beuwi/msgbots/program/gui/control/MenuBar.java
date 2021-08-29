package org.beuwi.msgbots.program.gui.control;

import javafx.collections.ObservableList;

import org.beuwi.msgbots.program.gui.layout.HBox;

public class MenuBar extends HBox {
    public MenuBar() {
        setFittable(true);
        setMinHeight(20);
        setPrefHeight(20);
        getStyleClass().add("menu-bar");
    }

    // ListView처럼 동작해야 하므로 getItems로 재선언
    public ObservableList<MenuButton> getItems() {
        return (ObservableList) getChildren();
    }
}