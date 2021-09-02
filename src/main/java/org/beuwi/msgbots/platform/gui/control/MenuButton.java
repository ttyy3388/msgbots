package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.scene.control.MenuItem;

public class MenuButton extends Button {
	public MenuButton() {
		this(null);
	}

	public MenuButton(String text) {
		setPadding(new Insets(0, 10, 0, 10));
		setMinWidth(40);
		setMinHeight(20);
		// setPrefWidth(40);
		// setPrefHeight(60);

		if (text != null) {
			setText(text);
		}

		getStyleClass().add("menu-button");
	}

	public void setContextMenu(ContextMenu menu) {
		menu.setNode(this);
	}

	public void setMenuItems(MenuItem... items) {
		setContextMenu(new ContextMenu(items));
	}
}