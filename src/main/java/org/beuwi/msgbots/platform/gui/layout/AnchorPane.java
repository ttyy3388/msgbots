package org.beuwi.msgbots.platform.gui.layout;

import javafx.scene.Node;

import org.beuwi.msgbots.platform.gui.base.Layout;

public class AnchorPane extends javafx.scene.layout.AnchorPane implements Layout {
	private static final String DEFAULT_STYLE_CLASS = "anchor-pane";

	public AnchorPane() {
		this(null);
	}

	public AnchorPane(Node content) {
		if (content != null) {
			getChildren().setAll(content);
		}

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public Node findById(String id) {
		return findById(this, id);
	};

	public void setTopAnchor(double anchor) {
		setTopAnchor(this, anchor);
	}
	public void setRightAnchor(double anchor) {
		setRightAnchor(this, anchor);
	}
	public void setBottomAnchor(double anchor) {
		setBottomAnchor(this, anchor);
	}
	public void setLeftAnchor(double anchor) {
		setLeftAnchor(this, anchor);
	}

	/* public static void setAnchor(Node child, Anchor value) {
		/* System.out.println(value.getTop() + " : " +
				value.getRight() + " : " +
				value.getBottom() + " : " +
				value.getLeft()); */
		/* setTopAnchor(child, value.getTop());
		setRightAnchor(child, value.getRight());
		setBottomAnchor(child, value.getBottom());
		setLeftAnchor(child, value.getLeft());
	} */
}