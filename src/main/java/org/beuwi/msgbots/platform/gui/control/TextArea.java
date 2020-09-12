package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.control.SeparatorMenuItem;

public class TextArea extends javafx.scene.control.TextArea
{
    private static final String DEFAULT_STYLE_CLASS = "text-area";

	public TextArea()
	{
		setContextMenu(new ContextMenu
		(
			new MenuItem("Undo", "Ctrl + Z" , event -> this.undo()),
		    new MenuItem("Redo", "Ctrl + Y" , event -> this.redo()),
		    new SeparatorMenuItem(),
			new MenuItem("Cut", "Ctrl + X", event -> this.cut()),
			new MenuItem("Copy", "Ctrl + C", event -> this.copy()),
			new MenuItem("Paste", "Ctrl + V", event -> this.paste()),
			new SeparatorMenuItem(),
			new MenuItem("Select All", "Ctrl + A", event -> this.selectAll())
		));

		// getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}
