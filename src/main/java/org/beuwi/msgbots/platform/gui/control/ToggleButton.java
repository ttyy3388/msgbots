package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;

public class ToggleButton extends javafx.scene.control.ToggleButton
{
    private static final String DEFAULT_STYLE_CLASS = "toggle-button";

    public BooleanProperty getSelectedProperty()
    {
        return selectedProperty();
    }
}