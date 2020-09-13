package org.beuwi.msgbots.platform.gui.control;

import com.jfoenix.controls.JFXToggleButton;

public class ToggleButton extends JFXToggleButton
{
    public static final String DEFAULT_STYLE_CLASS = "toggle-button";

    public ToggleButton()
    {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }
}