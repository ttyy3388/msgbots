package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
		Label name = new Label(text);
		name.setMinWidth(150);

		if (command != null) {
			setAccelerator(KeyCombination.keyCombination(command));
		}

		setGraphic(new HBox(name));
		setOnAction(handler);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public Menu visible(boolean visible) {
		setVisible(visible);
		return this;
	}

	public Menu disable(boolean disable) {
		setDisable(disable);
		return this;
	}

	public Menu disable(BooleanProperty property) {
		return disable((ReadOnlyBooleanProperty) property);
	}

	// Disable Property
	public Menu disable(ReadOnlyBooleanProperty property) {
		parentPopupProperty().addListener(event -> {
			ContextMenu parent = getParent();
			if (parent != null) {
				parent.showingProperty().addListener(change -> {
					setDisable(property.get());
				});
			}
		});
		/* property.addListener((observable, oldValue, newValue) -> {
			setDisable(newValue);
		}); */

		return this;
	}

	public Menu enable(BooleanProperty property) {
		return enable((ReadOnlyBooleanProperty) property);
	}
	public Menu enable(ReadOnlyBooleanProperty property) {
		parentPopupProperty().addListener(event -> {
			ContextMenu parent = getParent();
			if (parent != null) {
				parent.showingProperty().addListener(change -> {
					setDisable(!property.get());
				});
			}
		});
		/* property.addListener(change -> {
			setDisable(!property.get());
		}); */

		return this;
	}

	public ContextMenu getParent() {
		return (ContextMenu) getParentPopup();
	}
}
