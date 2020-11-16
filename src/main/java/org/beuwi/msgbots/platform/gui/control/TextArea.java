package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

public class TextArea extends javafx.scene.control.TextArea
{
	private static final String DEFAULT_STYLE_CLASS = "text-area";

	private static final int DEFAULT_PREF_WIDTH = 200;
	private static final int DEFAULT_PREF_HEIGHT = 50;

	private final ContextMenu menu = new ContextMenu
	(
		new Menu("Undo", "Ctrl + Z" , event -> this.undo()),
		new Menu("Redo", "Ctrl + Y" , event -> this.redo()),
		new Separator(),
		new Menu("Cut", "Ctrl + X", event -> this.cut()),
		new Menu("Copy", "Ctrl + C", event -> this.copy()),
		new Menu("Paste", "Ctrl + V", event -> this.paste()),
		new Separator(),
		new Menu("Select All", "Ctrl + A", event -> this.selectAll())
	);

	public TextArea()
	{
		setContextMenu(menu);

		setPrefHeight(DEFAULT_PREF_WIDTH);
		setPrefHeight(DEFAULT_PREF_HEIGHT);

		// getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setStyleClass(String style)
	{
		getStyleClass().setAll(style);
	}

	public void addStyleClass(String style)
	{
		getStyleClass().add(style);
	}

	public DoubleProperty getScrollTopProperty()
	{
		return scrollTopProperty();
	}

	public DoubleProperty getScrollLeftProperty()
	{
		return scrollLeftProperty();
	}

	public StringProperty getTextProperty()
	{
		return textProperty();
	}
}