package org.beuwi.msgbots.platform.gui.layout;

import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

import org.beuwi.msgbots.platform.gui.base.Layout;

public class ScrollPane extends javafx.scene.control.ScrollPane implements Layout {
	private static final String DEFAULT_STYLE_CLASS = "scroll-pane";

	private static final double DEFAULT_SCROLL_SPEED = 0.005;

	public ScrollPane() {
		this(null);
	}

	public ScrollPane(Node node) {
		if (node != null) {
			setContent(node);
		}

		addEventFilter(ScrollEvent.SCROLL, event -> {
			setHvalue(getHvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
			setVvalue(getVvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
		});

		setFitToWidth(true);
		setFitToHeight(true);

		setHbarPolicy(ScrollBarPolicy.ALWAYS);
		setVbarPolicy(ScrollBarPolicy.ALWAYS);

		// addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public Node findById(String id) {
		return findById(this, id);
	};
}