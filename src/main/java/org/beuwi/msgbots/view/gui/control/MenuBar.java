package org.beuwi.msgbots.view.gui.control;

import javafx.collections.ObservableList;

import org.beuwi.msgbots.view.gui.layout.HBox;

public class MenuBar extends HBox {
    public MenuBar() {
        setFitContent(true);
        setMinHeight(20);
        setPrefHeight(20);
        getStyleClass().add("menu-bar");
    }

    // ListView처럼 동작해야 하므로 getItems로 재선언
    public ObservableList<MenuButton> getItems() {
        return (ObservableList) getChildren();
    }
}