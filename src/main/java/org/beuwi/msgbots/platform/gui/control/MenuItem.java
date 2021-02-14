package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.binding.BooleanExpression;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;

public class MenuItem extends javafx.scene.control.MenuItem {
	private static final String DEFAULT_STYLE_CLASS = "menu";

	public MenuItem(String text) {
		this(text, (String) null);
	}

	public MenuItem(String text, String command) {
		this(text, command, null);
	}

	public MenuItem(String text, EventHandler handler) {
		this(text, null, handler);
	}

	public MenuItem(String text, String command, EventHandler handler) {
		Label name = new Label(text);
		name.setMinWidth(150);

		if (command != null) {
			setAccelerator(KeyCombination.keyCombination(command));
		}

		setGraphic(new HBox(name));
		setOnAction(handler);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public MenuItem visible(boolean visible) {
		setVisible(visible);
		return this;
	}

	public MenuItem disable(boolean disable) {
		setDisable(disable);
		return this;
	}

	// Disable Property
	public MenuItem disable(BooleanExpression property) {
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

	/* public MenuItem enable(BooleanProperty property) {
		return enable((ReadOnlyBooleanProperty) property);
	} */
	public MenuItem enable(BooleanExpression property) {
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
