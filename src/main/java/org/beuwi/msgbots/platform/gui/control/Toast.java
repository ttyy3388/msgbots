package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.control.Label;

// Toast Message
public class Toast extends HBox
{
	private static final String DEFAULT_STYLE_CLASS = "toast";
	private final Label label;

	public Toast(String message)
	{
		this.label = new Label(message);

		setMinHeight(100);
		setPrefHeight(100);

		getChildren().add(label);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}