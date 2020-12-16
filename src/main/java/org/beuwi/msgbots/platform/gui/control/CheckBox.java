package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;

public class CheckBox extends javafx.scene.control.CheckBox
{
    public BooleanProperty getSelectedProperty()
    {
        return selectedProperty();
    }
}
