package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;

// @DefaultProperty("items")
public class HBox<T> extends javafx.scene.layout.HBox {
	private static final String DEFAULT_STYlE_CLASS = "hbox";

	private final BooleanProperty fittable = new SimpleBooleanProperty();

	private final InvalidationListener listener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			for (Node child : getChildren()) {
				if (child instanceof Region) {
					((Region) child).setPrefHeight(getHeight());
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
		fittableProperty().set(fittable);
	}

	public boolean isFittable() {
		return fittableProperty().get();
	}

	public BooleanProperty fittableProperty() {
		return fittable;
	}
}