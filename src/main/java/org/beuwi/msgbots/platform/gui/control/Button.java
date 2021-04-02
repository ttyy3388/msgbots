package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;

import org.beuwi.msgbots.platform.gui.enums.ButtonType;

public class Button extends javafx.scene.control.Button {
    private static final Insets DEFAULT_PADDING_INSETS = new Insets(0, 10, 0, 10);

	private static final PseudoClass STYLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("styled");

	private static final String DEFAULT_STYLE_CLASS = "button";

	private static final String ACTION_STYLE_CLASS = "action";
	private static final String CANCEL_STYLE_CLASS = "cancel";

	// private static final int DEFAULT_MIN_WIDTH = 50;
	private static final int DEFAULT_MIN_HEIGHT = 20;

	private static final int DEFAULT_PREF_WIDTH = 70;
	private static final int DEFAULT_PREF_HEIGHT = 25;

	public Button() {
		this(null);
	}

	public Button(String text) {
		if (text != null) {
			setText(text);
		}

		styledProperty().addListener(change -> {
			pseudoClassStateChanged(STYLED_PSEUDO_CLASS, getStyled());

			if (getStyled()) {
				// setMinWidth(DEFAULT_MIN_WIDTH);
				setMinHeight(DEFAULT_MIN_HEIGHT);
				// setPrefWidth(DEFAULT_PREF_WIDTH);
				setPrefHeight(DEFAULT_PREF_HEIGHT);
			}

			// 기본 타입 지정
			setType(ButtonType.ACTION);
		});

		typeProperty().addListener(change -> {
		    // 스타일 클래스 초기화
		    getStyleClass().setAll(DEFAULT_STYLE_CLASS);

			if (getType().equals(ButtonType.ACTION)) {
				getStyleClass().add(ACTION_STYLE_CLASS);
			}
			else if (getType().equals(ButtonType.CANCEL)) {
				getStyleClass().add(CANCEL_STYLE_CLASS);
			}
		});

		// 텍스트가 입력됐으면 여백 값 입력
		textProperty().addListener((observable, oldValue, newValue) -> {
			if (getText() != null) {
				setPadding(DEFAULT_PADDING_INSETS);
			}
		});

		// setType(Type.CANCEL);
	}

	/* public void setMenu(ContextMenu menu) {
		menu.setNode(this);
	} */

	private final ObjectProperty<ButtonType> typeProperty = new SimpleObjectProperty(null);
	public void setType(ButtonType type) {
		typeProperty.set(type);
	}
	public ButtonType getType() {
		return typeProperty.get();
	}
	public ObjectProperty<ButtonType> typeProperty() {
		return typeProperty;
	}

	private final BooleanProperty styledProperty = new SimpleBooleanProperty(false);
	public void setStyled(boolean styled) {
		styledProperty.set(styled);
	}
	public boolean getStyled() {
		return styledProperty.get();
	}
	public BooleanProperty styledProperty() {
		return styledProperty;
	}
}
