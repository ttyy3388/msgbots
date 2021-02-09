package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// @DefaultProperty("content")
public class ShadowPane extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "shadow-pane";

	private static final DropShadow DEFAULT_DROP_SHADOW = new DropShadow(
		BlurType.THREE_PASS_BOX, Color.color(0, 0, 0, 0.8), 10, 0, 0, 0
	);
	private static final Insets DEFAULT_PADDING_VALUE = new Insets(5);

	private final StackPane contentArea = new StackPane();

	public ShadowPane() {
		this(null);
	}

	public ShadowPane(Node content) {
		if (content != null) {
			setContent(content);
		}

		contentArea.setEffect(DEFAULT_DROP_SHADOW);
		contentArea.setPadding(DEFAULT_PADDING_VALUE);

		getChildren().setAll(contentArea);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	// private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty(null);
	public void setContent(Node content) {
		contentArea.getChildren().setAll(content);
	}
	public Node getContent() {
		return contentArea.getChildren().get(0);
	}
	/* public ObjectProperty<Node> contentProperty() {
		return contentProperty;
	} */

	/* @Override
	public void setPadding(double padding) {
		super.setPadding(padding);
	} */
}