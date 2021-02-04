package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import org.beuwi.msgbots.platform.gui.base.Control;
import org.beuwi.msgbots.platform.gui.base.Layout;

public class HBox<T> extends javafx.scene.layout.HBox implements Layout {
    private static final String DEFAULT_STYLE_CLASS = "hbox";

    private final BooleanProperty fittableProperty = new SimpleBooleanProperty();

    private final InvalidationListener listener = new InvalidationListener() {
        @Override
        public void invalidated(Observable observable) {
            for (Node item : getChildren()) {
                if (item instanceof Region) {
                    ((Region) item).setPrefHeight(getHeight());
                }
            }
        }
    };

    public HBox() {
        this(null);
    }

    public HBox(Node... items) {
        if (items != null) {
            getChildren().setAll(items);
        }

        fittableProperty().addListener(event-> {
            if (isFittable()) {
                heightProperty().addListener(listener);
            }
            else {
                heightProperty().removeListener(listener);
            }
        });

        setFittable(true); // Default
        // setOnMouseClicked();
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    public Node findById(String id) {
        return findById(this, id);
    };

    /* public int findItem(String id) {
        for (int index = 0 ; index < getItems().size() ; index ++) {
            if (getItems().get(index).getId().equals(id)) {
                return index;
            }
        }

        return -1;
    } */

    public void setFittable(boolean fittable) {
        this.fittableProperty.set(fittable);
    }

    public boolean isFittable() {
        return fittableProperty.get();
    }

    public BooleanProperty fittableProperty() {
        return fittableProperty;
    }
}