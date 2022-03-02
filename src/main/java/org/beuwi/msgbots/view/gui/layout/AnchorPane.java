package org.beuwi.msgbots.view.gui.layout;

import javafx.scene.Node;

import org.beuwi.msgbots.view.gui.layout.base.AnchorPaneBase;

public class AnchorPane extends AnchorPaneBase {
	public AnchorPane() {
		this(null);
	}

	public AnchorPane(Node... children) {
		if (children != null) {
			initChildren(children);
		}

		addStyleClass("anchor-pane");
	}
}