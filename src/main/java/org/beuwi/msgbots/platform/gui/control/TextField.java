package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.StringProperty;

public class TextField extends javafx.scene.control.TextField
{
	private static final String DEFAULT_STYLE_CLASS = "text-field";

    private static final int DEFAULT_PREF_WIDTH = 200;
    private static final int DEFAULT_PREF_HEIGHT = 25;

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

	public TextField()
	{
		setContextMenu(menu);

		setPrefWidth(DEFAULT_PREF_WIDTH);
		setPrefHeight(DEFAULT_PREF_HEIGHT);

		// addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public StringProperty getTextProperty()
	{
		return textProperty();
	}
}
