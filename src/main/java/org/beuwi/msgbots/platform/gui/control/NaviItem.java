package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.control.base.TabItemBase;
import org.beuwi.msgbots.platform.gui.layout.HBox;

public class NaviItem extends TabItemBase {

    private static final String DEFAULT_STYLE_CLASS = "navi-item";

	private static final String LABEL_STYLE_CLASS = "tab-label";

	private static final Insets DEFAULT_ITEM_PADDING = new Insets(0, 10, 0, 10);

	private static final int DEFAULT_ITEM_WIDTH = 150;
	private static final int DEFAULT_ITEM_HEIGHT = 30;

	// private static final Pos DEFAULT_HEADER_ALIGNMENT = Pos.CENTER;

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
		setHeader(label);

        // label.setText(control.getText());
        // label.setAlignment(DEFAULT_HEADER_ALIGNMENT);
       	label.getStyleClass().add(LABEL_STYLE_CLASS);

		textProperty().addListener(change -> {
			this.setId(getText());
			label.setText(getText());
		});

		setPadding(DEFAULT_ITEM_PADDING);
		setMinWidth(DEFAULT_ITEM_WIDTH); // 너비 강제 지정
		setPrefWidth(DEFAULT_ITEM_WIDTH); // 너비 강제 지정
		setMaxWidth(DEFAULT_ITEM_WIDTH); // 너비 강제 지정
		setMinHeight(DEFAULT_ITEM_HEIGHT); // 높이 강제 지정
		setPrefHeight(DEFAULT_ITEM_HEIGHT); // 높이 강제 지정
		setMaxHeight(DEFAULT_ITEM_HEIGHT); // 높이 강제 지정
		getStyleClass().add(DEFAULT_STYLE_CLASS);
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
