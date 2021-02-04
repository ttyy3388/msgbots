package org.beuwi.msgbots.platform.gui.control;

public class TextField extends javafx.scene.control.TextField {
	private static final String DEFAULT_STYLE_CLASS = "text-field";

    private static final int DEFAULT_PREF_WIDTH = 200;
    private static final int DEFAULT_PREF_HEIGHT = 25;

    private final ContextMenu menu = new ContextMenu(
		new MenuItem("Undo", "Ctrl + Z" , event -> this.undo()),
		new MenuItem("Redo", "Ctrl + Y" , event -> this.redo()),
		new Separator(),
		new MenuItem("Cut", "Ctrl + X", event -> this.cut()),
		new MenuItem("Copy", "Ctrl + C", event -> this.copy()),
		new MenuItem("Paste", "Ctrl + V", event -> this.paste()),
		new Separator(),
		new MenuItem("Select All", "Ctrl + A", event -> this.selectAll())
	);

	public TextField() {
		setContextMenu(menu);
		setPrefWidth(DEFAULT_PREF_WIDTH);
		setPrefHeight(DEFAULT_PREF_HEIGHT);
		// getItems().addAll(DEFAULT_STYLE_CLASS);
	}
}
