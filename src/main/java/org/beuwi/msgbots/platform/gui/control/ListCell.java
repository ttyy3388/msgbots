package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ReadOnlyBooleanProperty;

public class ListCell<T> extends javafx.scene.control.ListCell<T>
{
    public ReadOnlyBooleanProperty getEmptyProperty()
    {
        return emptyProperty();
    }
}
