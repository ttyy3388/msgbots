package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;

public class ListCell<T> extends javafx.scene.control.ListCell<T>
{
    public ReadOnlyDoubleProperty getWidthProperty()
    {
        return widthProperty();
    }

    public DoubleProperty getMaxWidthProperty()
    {
        return maxWidthProperty();
    }

    public ReadOnlyBooleanProperty getEmptyProperty()
    {
        return emptyProperty();
    }
}
