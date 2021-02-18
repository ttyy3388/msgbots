package org.beuwi.msgbots.platform.gui.control;

public class ToggleSwitch extends com.jfoenix.controls.JFXToggleButton {
    private static final String DEFAULT_STYLE_CLASS = "toggle-switch";

    private static final int DEFAULT_SIZE_VALUE = 6;

    public ToggleSwitch() {
        setSize(DEFAULT_SIZE_VALUE);
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }
}