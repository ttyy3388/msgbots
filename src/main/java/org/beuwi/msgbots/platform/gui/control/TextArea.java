package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class TextArea extends javafx.scene.control.TextArea {
	private final BooleanProperty autoScrollProperty = new SimpleBooleanProperty();
	private final InvalidationListener autoScrollListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			// SetText 호출 시에는 반응 안하므로 다른 방법 사용
			// setScrollTop(Double.MAX_VALUE);
			// setScrollLeft(Double.MIN_VALUE);
			appendText("");
		}
	};

	private static final String DEFAULT_STYLE_CLASS = "text-area";

	private static final int DEFAULT_PREF_WIDTH = 200;
	private static final int DEFAULT_PREF_HEIGHT = 50;

	private final ContextMenu menu = new ContextMenu(
		new MenuItem("Undo", "Ctrl + Z" , event -> this.undo()).disable(true).enable(undoableProperty()),
		new MenuItem("Redo", "Ctrl + Y" , event -> this.redo()).disable(true).enable(redoableProperty()),
		new Separator(),
		new MenuItem("Cut", "Ctrl + X", event -> this.cut()).enable(editableProperty()),
		new MenuItem("Copy", "Ctrl + C", event -> this.copy()),
		new MenuItem("Paste", "Ctrl + V", event -> this.paste()).enable(editableProperty()),
		new Separator(),
		new MenuItem("Select All", "Ctrl + A", event -> this.selectAll())
	);

	public TextArea() {
		autoScrollProperty().addListener(change -> {
			if (isAutoScroll()) {
				textProperty().addListener(autoScrollListener);
			}
			else {
				textProperty().removeListener(autoScrollListener);
			}
		});

		setContextMenu(menu);
		setPrefHeight(DEFAULT_PREF_WIDTH);
		setPrefHeight(DEFAULT_PREF_HEIGHT);
		// getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setAutoScroll(boolean value) {
		autoScrollProperty.set(value);
	}
	public boolean isAutoScroll() {
		return autoScrollProperty.get();
	}
	public BooleanProperty autoScrollProperty() {
		return autoScrollProperty;
	}
}