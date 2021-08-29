package org.beuwi.msgbots.program.gui.layout;

import javafx.scene.Node;

public class AnchorPane extends javafx.scene.layout.AnchorPane {
	public AnchorPane() {
		this(null);
	}

	public AnchorPane(Node... children) {
		if (children != null) {
			getChildren().setAll(children);
		}

		getStyleClass().add("anchor-pane");
	}

	public static void setFitAnchor(Node node) {
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
	}
}