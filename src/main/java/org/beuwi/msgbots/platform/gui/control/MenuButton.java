package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.control.Button;

public class MenuButton extends Button
{
    final int DEFAULT_WIDTH = 40;
    final int DEFAULT_HEIGHT = 30;

    public MenuButton()
    {
        setPrefWidth(DEFAULT_WIDTH);
        setPrefHeight(DEFAULT_HEIGHT);

        getStyleClass().add("menu-button");
    }

    public void setMenu(ContextMenu menu)
    {
        menu.setNode(this);
    }

    public void setMenus(MenuItem... items)
    {
        setMenu(new ContextMenu(items));
    }
}