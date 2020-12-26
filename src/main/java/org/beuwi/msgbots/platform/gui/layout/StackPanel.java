package org.beuwi.msgbots.platform.gui.layout;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;

// @DefaultProperty("items")
public class StackPanel extends javafx.scene.layout.StackPane {
	private static final String DEFAULT_STYLE_CLASS = "stack-panel";

	public StackPanel() {
		this(null);
	}

	public StackPanel(Node... content) {
	    if (content != null) {
			getItems().setAll(content);
        }

	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setPadding(double padding) {
		super.setPadding(new Insets(padding));
	}

	public ObservableList<Node> getItems() {
		return getChildren();
	}

	/* public static void setMargin(StackPanel panel, double value) {
		setMargin(panel, new Insets(value));
	} */
}