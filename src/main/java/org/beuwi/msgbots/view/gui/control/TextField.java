package org.beuwi.msgbots.view.gui.control;

import org.beuwi.msgbots.view.util.StdActions;

public class TextField extends javafx.scene.control.TextField {
	public TextField() {
		this(null);
	}

	public TextField(String text) {
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
	}
}
