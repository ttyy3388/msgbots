package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.Collection;

@DefaultProperty("items")
public class HBox<T> extends javafx.scene.layout.HBox {
	private static final String DEFAULT_STYlE_CLASS = "hbox";

	// Fittable Content Property
	private final BooleanProperty fittable = new SimpleBooleanProperty();

	private final InvalidationListener listener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			for (Node item : getItems()) {
				if (item instanceof Region) {
					((Region) item).setPrefHeight(getHeight());
				}
			}
		}
	};

	public HBox()
	{
		this(null);
	}

	public HBox(Node... items)
	{
		if (items != null) {
			getItems().addAll(items);
		}

		fittableProperty().addListener(event -> {
			if (isFittable()) {
				heightProperty().addListener(listener);
			}
			else {
				heightProperty().removeListener(listener);
			}
		});

		getStyleClass().add(DEFAULT_STYlE_CLASS);
	}

	public void setFittable(boolean fittable) {
		this.fittable.set(fittable);
	}

	public boolean isFittable() {
		return fittable.get();
	}

	/* public int getItemIndex(String id) {
		for (int index = 0 ; index < getItems().size() ; index ++) {
			if (getItems().get(index).getId().equals(id)) {
				return index;
			}
		}

		return -1;
	} */

	public ObservableList<Node> getItems() {
		return getChildren();
	}

	public BooleanProperty fittableProperty() {
		return fittable;
	}
}