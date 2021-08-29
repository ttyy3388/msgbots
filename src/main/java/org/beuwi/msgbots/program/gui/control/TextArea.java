package org.beuwi.msgbots.program.gui.control;

import org.beuwi.msgbots.program.utils.GlobalActions;

public class TextArea extends javafx.scene.control.TextArea {
	public TextArea() {
		this(null);
	}

	public TextArea(String text) {
		if (text != null) {
			setText(text);
		}

		setContextMenu(new ContextMenu(
			GlobalActions.UNDO.custom(event -> undo()).toMenu(),
			GlobalActions.REDO.custom(event -> redo()).toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.CUT.custom(event -> cut()).toMenu(),
			GlobalActions.COPY.custom(event -> copy()).toMenu(),
			GlobalActions.PASTE.custom(event -> paste()).toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.SELECT_ALL.custom(event -> selectAll()).toMenu()
		));

		setPrefHeight(200);
		setPrefHeight(50);
	}
}