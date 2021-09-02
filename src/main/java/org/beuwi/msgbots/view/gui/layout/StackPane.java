package org.beuwi.msgbots.view.gui.layout;

import javafx.scene.Node;

public class StackPane extends javafx.scene.layout.StackPane {
	public StackPane() {
		this(null);
	}
	public StackPane(Node... children) {
		if (children != null) {
			getChildren().setAll(children);
		}

		getStyleClass().add("stack-pane");
	}
}