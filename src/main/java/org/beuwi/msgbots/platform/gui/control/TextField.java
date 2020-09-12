package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.control.SeparatorMenuItem;

public class TextField extends javafx.scene.control.TextField
{
    private static final String DEFAULT_STYLE_CLASS = "text-field";

	public TextField()
	{
		setContextMenu(new ContextMenu
		(
			new Menu("Undo", "Ctrl + Z" , event -> this.undo()),
		    new Menu("Redo", "Ctrl + Y" , event -> this.redo()),
		    new SeparatorMenuItem(),
			new Menu("Cut", "Ctrl + X", event -> this.cut()),
			new Menu("Copy", "Ctrl + C", event -> this.copy()),
			new Menu("Paste", "Ctrl + V", event -> this.paste()),
			new SeparatorMenuItem(),
			new Menu("Select All", "Ctrl + A", event -> this.selectAll())
		));

		// getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
