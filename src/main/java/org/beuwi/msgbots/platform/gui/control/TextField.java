package org.beuwi.msgbots.platform.gui.control;

public class TextField extends javafx.scene.control.TextField {
	private static final String DEFAULT_STYLE_CLASS = "text-field";

    // private static final int DEFAULT_PREF_WIDTH = 200;
    // private static final int DEFAULT_MAX_HEIGHT = 25;

	private final ContextMenu menu = new ContextMenu(
		new MenuItem("Undo", "Ctrl + Z" , event -> this.undo()).disable(true).enable(undoableProperty()),
		new MenuItem("Redo", "Ctrl + Y" , event -> this.redo()).disable(true).enable(redoableProperty()),
		new Separator(),
		new MenuItem("Cut", "Ctrl + X", event -> this.cut()).enable(editableProperty()),
		new MenuItem("Copy", "Ctrl + C", event -> this.copy()),
		new MenuItem("Paste", "Ctrl + V", event -> this.paste()).enable(editableProperty()),
		new Separator(),
		new MenuItem("Select All", "Ctrl + A", event -> this.selectAll())
	);

	public TextField() {
		setContextMenu(menu);
		// setMaxHeight(DEFAULT_MAX_HEIGHT);
		// setMinHeight(DEFAULT_PREF_HEIGHT);
		// setPrefWidth(DEFAULT_PREF_WIDTH);
		// setPrefHeight(DEFAULT_PREF_HEIGHT);
		// getItems().addAll(DEFAULT_STYLE_CLASS);
	}
}
