package org.beuwi.msgbots.platform.gui.layout;

import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// @DefaultProperty("content")
public class ShadowPane extends StackPane {
	private static final String DEFAULT_STYLE_CLASS = "shadow-panel";
	private static final DropShadow DEFAULT_DROP_SHADOW = new DropShadow(
		BlurType.THREE_PASS_BOX, Color.color(0, 0, 0, 0.8), 10, 0, 0, 0
	);

	public ShadowPane() {
		this(null);
	}

	public ShadowPane(Node content) {
		if (content != null) {
			getChildren().setAll(content);
		}

		setEffect(DEFAULT_DROP_SHADOW);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	// private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty(null);
	public void setContent(Node content) {
		getChildren().setAll(content);
	}
	public Node getContent() {
		return getChildren().get(0);
	}
	/* public ObjectProperty<Node> contentProperty() {
		return contentProperty;
	} */

	/* @Override
	public void setPadding(double padding) {
		super.setPadding(padding);
	} */
}