package org.beuwi.msgbots.platform.gui.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public enum Event {
    CONTROL_DISABLED,
    CONTROL_HOVERED,
	CONTROL_FOCUSED /* {
		@Override
		public String toString() {
			return "focused";
		}
	}, */,
	WIDTH_CHANGED /* {
		@Override
		public String toString() {
			return "resized";
		}
	}; */,
	HEIGHT_CHANGED,
	ID_CHANGED;

	public static ObservableValue toProperty(Node node, Event event) {
        ObservableValue property = null;

        switch (event) {
            case CONTROL_DISABLED :
                property = node.disabledProperty();
                break;
            case CONTROL_FOCUSED :
                property = node.focusedProperty();
                break;
            case CONTROL_HOVERED :
                property = node.hoverProperty();
                break;
            case WIDTH_CHANGED :
                if (node instanceof Pane) {
                    Pane pane = (Pane) node;
                    property = pane.widthProperty();
                }
                break;
            case HEIGHT_CHANGED :
                if (node instanceof Pane) {
                    Pane pane = (Pane) node;
                    property = pane.heightProperty();
                }
                break;
            case ID_CHANGED :
                property = node.idProperty();
                break;
        }

        return property;
	}
}
