package org.beuwi.msgbots.view.gui.layout;

import javafx.scene.Node;

import org.beuwi.msgbots.view.gui.layout.base.StackPaneBase;

public class StackPane extends StackPaneBase {
	public StackPane() {
		this(null);
	}
	public StackPane(Node... children) {
		if (children != null) {
			initChildren(children);
		}

		addStyleClass("stack-pane");
	}
}