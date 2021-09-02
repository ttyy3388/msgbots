package org.beuwi.msgbots.platform.gui.control;

import org.beuwi.msgbots.platform.utils.StdActions;

public class TextArea extends javafx.scene.control.TextArea {
	public TextArea() {
		this(null);
	}

	public TextArea(String text) {
		if (text != null) {
			setText(text);
		}

		setContextMenu(new ContextMenu(
			StdActions.UNDO.handler(event -> undo()).toMenuItem(),
			StdActions.REDO.handler(event -> redo()).toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.CUT.handler(event -> cut()).toMenuItem(),
			StdActions.COPY.handler(event -> copy()).toMenuItem(),
			StdActions.PASTE.handler(event -> paste()).toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.SELECT_ALL.handler(event -> selectAll()).toMenuItem()
		));

		setPrefHeight(200);
		setPrefHeight(50);
	}
}