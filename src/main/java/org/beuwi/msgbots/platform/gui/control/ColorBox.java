package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.Node;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class ColorBox extends HBox<StackPane> {
	private static final String DEFAULT_STYLE_CLASS = "color-box";

	private final StackPane content = new StackPane();
	private final ColorBar header = new ColorBar();

	{
		HBox.setHgrow(content, Priority.ALWAYS);
	}

	public ColorBox() {
		getChildren().setAll(header, content);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setHeader(Node header) {
		this.header.getChildren().setAll(header);
	}

	public void setContent(Node content) {
		this.content.getChildren().setAll(content);
	}

	public ColorBar getHeader() {
		return header;
	}

	public Node getContent() {
		return content.getChildren().get(0);
	}

	public ColorBar getColorBar() {
		return header;
	}
}