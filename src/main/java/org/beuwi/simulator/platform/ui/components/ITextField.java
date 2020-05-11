package org.beuwi.simulator.platform.ui.components;

import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;

public class ITextField extends TextField
{
	{
		getStyleClass().add("ifx-text-field");
	}

	public ITextField()
	{
		setContextMenu(new IContextMenu
		(
			new IMenuItem("Undo", "Ctrl + Z" , event -> undo()),
			new IMenuItem("Redo", "Ctrl + Y" , event -> redo()),
			new SeparatorMenuItem(),
			new IMenuItem("Cut", "Ctrl + X", event -> cut()),
			new IMenuItem("Copy", "Ctrl + C", event -> copy()),
			new IMenuItem("Paste", "Ctrl + V", event -> paste()),
			new SeparatorMenuItem(),
			new IMenuItem("Select All", "Ctrl + A", event -> selectAll())
		));
	}
}