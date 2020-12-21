package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;

public class Menu extends javafx.scene.control.MenuItem {
	private static final String DEFAULT_STYLE_CLASS = "menu";

	public Menu(String text) {
		this(text, (String) null);
	}

	public Menu(String text, String command) {
		this(text, command, null);
	}

	public Menu(String text, EventHandler handler) {
		this(text, null, handler);
	}

	public Menu(String text, String command, EventHandler handler) {
		this(text, command, null, handler);
	}

	public Menu(String text, String command, BooleanProperty property, EventHandler handler) {
		this(text, command, (ReadOnlyBooleanProperty) property, handler);
	}

	public Menu(String text, String command, ReadOnlyBooleanProperty property, EventHandler handler) {
		Label name = new Label(text);
		name.setMinWidth(150);

		if (command != null) {
			setAccelerator(KeyCombination.keyCombination(command));
		}

		if (property != null) {
			property.addListener((observable, oldValue, newValue) -> {
				setDisable(!newValue);
			});
		}

		setGraphic(new HBox(name));
		setOnAction(handler);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
