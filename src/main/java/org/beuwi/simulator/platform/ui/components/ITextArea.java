package org.beuwi.simulator.platform.ui.components;

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
			new IMenuItem("Cut", "Ctrl + X", event -> this.cut()),
			new IMenuItem("Copy", "Ctrl + C", event -> this.copy()),
			new IMenuItem("Paste", "Ctrl + V", event -> this.paste())
		));
	}
}