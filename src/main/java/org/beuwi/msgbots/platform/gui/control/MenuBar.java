package org.beuwi.msgbots.platform.gui.control;

public class MenuBar extends HBox<MenuButton> {
	private static final String DEFAULT_STYlE_CLASS = "menu-bar";

	private static final int DEFAULT_MIN_HEIGHT = 20;
	private static final int DEFAULT_PREF_HEIGHT = 20;

	public MenuBar() {
		setMinHeight(DEFAULT_MIN_HEIGHT);
		setPrefHeight(DEFAULT_PREF_HEIGHT);
		getStyleClass().add(DEFAULT_STYlE_CLASS);
	}
}