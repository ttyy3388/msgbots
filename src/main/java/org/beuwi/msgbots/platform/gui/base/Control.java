package org.beuwi.msgbots.platform.gui.base;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.NumberExpression;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.beuwi.msgbots.platform.gui.control.ListView;

import java.util.List;

public interface Control {

    // default void addStyleClass(String style) { }
    // default void setStyleClass(String style) { }

    /* default Node getItem(Region target, String id) {
        return findById(target, id);
    } */

    default Node findById(Region target, String id) {
        List children = null;
        if (target instanceof ListView) {
            children = ((ListView) target).getItems();
        }
        else {
            // getChildren() 과 달리 리스트 수정은 불가능함 : 반환만 가능
            target.getChildrenUnmodifiable();
        }

        for (Object child : children) {
            if (child instanceof Node) {
                Node node = (Node) child;
                if (node.getId().equals(id)) {
                    return (Node) child;
                }
                else {
                    continue ;
                }
            }
			/* else {
				continue ;
			} */
        }

        return null;
    }

    /* default void addChangeListener(Node node, Event event, InvalidationListener listener) {
        Event.toProperty(node, event).addListener(listener);
    }
    default void addChangeListener(ObservableValue property, InvalidationListener listener) {
        property.addListener(listener);
    }
    default void removeChangeListener(Node node, Event event, InvalidationListener listener) {
        Event.toProperty(node, event).removeListener(listener);
    }
    default void removeChangeListener(ObservableValue property, InvalidationListener listener) {
        property.removeListener(listener);
    } */
}
