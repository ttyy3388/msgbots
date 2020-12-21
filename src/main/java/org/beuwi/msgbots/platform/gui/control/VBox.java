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

// @DefaultProperty("items")
public class VBox<T> extends javafx.scene.layout.VBox {
	private static final String DEFAULT_STYLE_CLASS = "vbox";

	private final BooleanProperty fittable = new SimpleBooleanProperty();

	private final InvalidationListener listener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			for (Node item : getItems()) {
				if (item instanceof Region) {
					((Region) item).setPrefWidth(getWidth());
				}
			}
		}
	};

	public VBox() {
		this(null);
	}

	public VBox(Node... items) {
		if (items != null) {
			getItems().addAll(items);
		}

		fittableProperty().addListener(event -> {
			if (isFittable()) {
				widthProperty().addListener(listener);
			}
			else {
				widthProperty().removeListener(listener);
			}
		});

		// setOnMouseClicked();
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public int findItem(String id) {
		for (int index = 0 ; index < getItems().size() ; index ++) {
			if (getItems().get(index).getId().equals(id)) {
				return index;
			}
		}

		return -1;
	}

	public void setFittable(boolean fittable) {
		this.fittable.set(fittable);
	}

	public boolean isFittable() {
		return fittable.get();
	}

	public ObservableList<Node> getItems() {
		return getChildren();
	}

	public BooleanProperty fittableProperty() {
		return fittable;
	}
}