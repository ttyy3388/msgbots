package org.beuwi.msgbots.platform.gui.layout;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

// @DefaultProperty("center")
public class BorderPanel extends BorderPane
{
    private static final String DEFAULT_STYLE_CLASS = "border-panel";

    public void setPadding(double padding)
    {
        setPadding(new Insets(padding));
    }

    public void addStyleClass(String style)
    {
        getStyleClass().add(style);
    }
}