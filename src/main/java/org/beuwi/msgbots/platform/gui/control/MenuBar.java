package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.stream.Collectors;

public class MenuBar extends HBox
{
	final int DEFAULT_HEIGHT = 20;

	private int index = 0;

	public MenuBar()
	{
		setPrefHeight(DEFAULT_HEIGHT);

		getChildren().addListener((ListChangeListener) change ->
		{
			while (change.next())
            {
                if (change.wasAdded())
                {
					getItems().get(index ++).setPrefHeight(DEFAULT_HEIGHT);
                }
            }
		});

		getStyleClass().add("menu-bar");
	}

	public void addItem(String name)
	{
		addItem(new MenuButton(name));
	}

	public void addItem(MenuButton button)
	{
		getItems().add(button);
	}

	public void addItems(MenuButton... buttons)
	{
		getChildren().addAll(buttons);
	}

	public List<MenuButton> getItems()
	{
		return getChildren().stream().map(tab -> (MenuButton) tab).collect(Collectors.toList());
	}

	public MenuButton getItem(int index)
	{
		return getItems().get(index);
	}
}