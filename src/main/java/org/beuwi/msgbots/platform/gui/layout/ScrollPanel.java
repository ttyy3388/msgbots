package org.beuwi.msgbots.platform.gui.layout;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;

// @DefaultProperty("content")
public class ScrollPanel extends javafx.scene.control.ScrollPane {
	private static final String DEFAULT_STYLE_CLASS = "scroll-panel";

	private static final double DEFAULT_SCROLL_SPEED = 0.005;

	public ScrollPanel() {
		this(null);
	}

	public ScrollPanel(Node node) {
		super(node);

		addEventFilter(ScrollEvent.SCROLL, event -> {
			setHvalue(getHvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
			setVvalue(getVvalue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
		});

		setHbarPolicy(ScrollBarPolicy.ALWAYS);
		setVbarPolicy(ScrollBarPolicy.ALWAYS);

		setFitToWidth(true);
		setFitToHeight(true);

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setPadding(double padding) {
		super.setPadding(new Insets(padding));
	}

	public ObservableList<Node> getItems() {
		return getChildren();
	}
}