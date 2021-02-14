package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.scene.control.MenuItem;

public class MenuButton extends Button {
	private static final String DEFAULT_STYLE_CLASS = "menu-button";
	private static final Insets DEFAULT_PADDING_INSETS = new Insets(0, 10, 0, 10);

	private static final int DEFAULT_MIN_WIDTH = 40;
	private static final int DEFAULT_MIN_HEIGHT = 20;

	// private static final int DEFAULT_PREF_WIDTH = 40;
	private static final int DEFAULT_PREF_HEIGHT = 30;

	public MenuButton() {
		this(null);
	}

	public MenuButton(String text) {
		setPadding(DEFAULT_PADDING_INSETS);
		setMinWidth(DEFAULT_MIN_WIDTH);
		setMinHeight(DEFAULT_MIN_HEIGHT);

		// setPrefWidth(DEFAULT_PREF_HEIGHT);
		// setPrefHeight(DEFAULT_PREF_HEIGHT);

		if (text != null) {
			setText(text);
		}

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setMenu(ContextMenu menu) {
		menu.setNode(this);
	}

	public void setMenus(MenuItem... items) {
		setMenu(new ContextMenu(items));
	}
}