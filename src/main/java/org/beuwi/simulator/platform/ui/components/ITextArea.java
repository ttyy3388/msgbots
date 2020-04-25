package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;

public class ITextArea extends TextArea
{
	{
		this.getStyleClass().add("ifx-text-area");
	}

	public ITextArea()
	{
		this.setContextMenu(new IContextMenu
		(
			new IMenuItem("Undo", "Ctrl + Z", event -> this.undo()),
			new IMenuItem("Redo", "Ctrl + Y", event -> this.redo()),
			new SeparatorMenuItem(),
			new IMenuItem("Cut", "Ctrl + X", event -> this.cut()),
			new IMenuItem("Copy", "Ctrl + C", event -> this.copy()),
			new IMenuItem("Paste", "Ctrl + V", event -> this.paste()),
			new SeparatorMenuItem(),
			new IMenuItem("Select All", "Ctrl + A", event -> this.selectAll())
		));
	}
}