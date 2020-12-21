package org.beuwi.msgbots.platform.gui.control;

import java.util.List;
import java.util.stream.Collectors;

public class MenuBar extends HBox<MenuButton> {
	private static final String DEFAULT_STYlE_CLASS = "menu-bar";

	private static final int DEFAULT_MIN_HEIGHT = 20;
	private static final int DEFAULT_PREF_HEIGHT = 20;

	public MenuBar() {
		setFittable(true);
		setMinHeight(DEFAULT_MIN_HEIGHT);
		setPrefHeight(DEFAULT_PREF_HEIGHT);
		getStyleClass().add(DEFAULT_STYlE_CLASS);
	}

	public MenuButton getMenu(int index) {
		return getMenus().get(index);
	}

	public List<MenuButton> getMenus() {
		return getChildren().stream().map(tab -> (MenuButton) tab).collect(Collectors.toList());
	}
}