package org.beuwi.msgbots.platform.gui.control;

import java.util.List;
import java.util.stream.Collectors;

public class MenuBar extends HBox
{
	public static final String DEFAULT_STYlE_CLASS = "menu-bar";

	public static final int DEFAULT_MIN_HEIGHT = 20;
	public static final int DEFAULT_PREF_HEIGHT = 20;

	private int index = 0;

	public MenuBar()
	{
		setMinHeight(DEFAULT_MIN_HEIGHT);
		setPrefHeight(DEFAULT_PREF_HEIGHT);

		getStyleClass().add(DEFAULT_STYlE_CLASS);
	}

	public void addMenu(String name)
	{
		addItem(new MenuButton(name));
	}

	public void addMenu(MenuButton button)
	{
		getItems().add(button);
	}

	public void addMenus(MenuButton... buttons)
	{
		getChildren().addAll(buttons);
	}

	public MenuButton getMenu(int index)
	{
		return getMenus().get(index);
	}

	public List<MenuButton> getMenus()
	{
		return getChildren().stream().map(tab -> (MenuButton) tab).collect(Collectors.toList());
	}
}