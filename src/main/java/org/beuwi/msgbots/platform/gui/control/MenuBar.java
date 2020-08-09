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
					getItems().get(index++).setPrefHeight(getPrefHeight());
                }
            }
		});

		getStyleClass().add("menu-bar");
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