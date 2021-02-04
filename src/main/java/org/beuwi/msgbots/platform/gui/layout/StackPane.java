package org.beuwi.msgbots.platform.gui.layout;

import javafx.scene.Node;

import org.beuwi.msgbots.platform.gui.base.Layout;

public class StackPane extends javafx.scene.layout.StackPane implements Layout {
	private static final String DEFAULT_STYLE_CLASS = "stack-pane";

	public StackPane() {
		this(null);
	}

	public StackPane(Node... children) {
	    if (children != null) {
			getChildren().setAll(children);
        }

		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public Node findById(String id) {
		return findById(this, id);
	};
}