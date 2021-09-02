package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.control.base.TabItemBase;
import org.beuwi.msgbots.platform.gui.layout.HBox;

public class NaviItem extends TabItemBase<NaviView> {
	// private static final int DEFAULT_ITEM_WIDTH = 150;
	// private static final int DEFAULT_ITEM_HEIGHT = 30;
	// private static final Pos DEFAULT_HEADER_ALIGNMENT = Pos.CENTER;

	private final HBox header = new HBox();
	private final Label label = new Label();

	{
		HBox.setHgrow(label, Priority.ALWAYS);
	}

	public NaviItem() {
		this(null);
	}
	public NaviItem(String title) {
		this(title, new Pane());
	}
	public NaviItem(String title, Node content) {
		if (title != null) {
			setId(title);
			setText(title);
		}

		if (content != null) {
			setContent(content);
		}
		setId(title);
		setHeader(header);

        // label.setText(control.getText());
        // label.setAlignment(Pos.CENTER);
       	label.getStyleClass().add("text-label");
		header.getStyleClass().add("header");
		header.getChildren().setAll(label);

		textProperty().addListener(change -> {
			this.setId(getText());
			label.setText(getText());
		});

		// 헤더 관련 옵션
		setPadding(new Insets(0, 10, 0, 10));
		// 너비 강제 지정
		// setMinWidth(150);
		// setPrefWidth(150);
		// setMaxWidth(150);
		// 높이 강제 지정
		setMinHeight(30);
		setPrefHeight(30);
		setMaxHeight(30);
		getStyleClass().add("navi-item");
	}

	public void setText(String text) {
		label.setText(text);
	}
	public String getText() {
		return label.getText();
	}
	public StringProperty textProperty() {
		return label.textProperty();
	}
}
