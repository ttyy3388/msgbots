package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.control.Skin;

public abstract class Control extends javafx.scene.control.Control {

    @Override
    public Skin<?> createDefaultSkin()
    {
        return setDefaultSkin();
    }

    public Skin<?> setDefaultSkin() { return null; }

    public void addStyleClass(String style)
    {
        getStyleClass().add(style);
    }

    public void setStyleClass(String style)
    {
        getStyleClass().setAll(style);
    }
}
