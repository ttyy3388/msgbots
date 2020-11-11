package org.beuwi.msgbots.platform.gui.control;

import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.property.ReadOnlyBooleanProperty;

public class ToggleSwitch extends JFXToggleButton
{
    private static final String DEFAULT_STYLE_CLASS = "toggle-switch";

    public ToggleSwitch()
    {
        setSize(6.0);

        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    public ReadOnlyBooleanProperty getSelectedProperty()
    {
        return selectedProperty();
    }
}