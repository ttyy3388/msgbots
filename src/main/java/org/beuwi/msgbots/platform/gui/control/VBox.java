package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;

// @DefaultProperty("items")
public class VBox<T> extends javafx.scene.layout.VBox {
	private static final String DEFAULT_STYLE_CLASS = "vbox";

	private final BooleanProperty fittable = new SimpleBooleanProperty();

	private final InvalidationListener listener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			for (Node child : getChildren()) {
				if (child instanceof Region) {
					((Region) child).setPrefWidth(getWidth());
				}
			}
		}
	};

	public VBox() {
		this(null);
	}

	public VBox(Node... items) {
		if (items != null) {
			getChildren().setAll(items);
		}

		fittableProperty().addListener(event -> {
			if (isFittable()) {
				widthProperty().addListener(listener);
			}
			else {
				widthProperty().removeListener(listener);
			}
		});

		getStyleClass().add(DEFAULT_STYLE_CLASS);
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