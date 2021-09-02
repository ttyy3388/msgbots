package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;

import org.beuwi.msgbots.platform.gui.type.ButtonType;

public class Button extends javafx.scene.control.Button {
	private static final PseudoClass STYLED_PSEUDO_CLASS = PseudoClass.getPseudoClass("styled");
	private static final PseudoClass ACTION_PSEUDO_CLASS = PseudoClass.getPseudoClass("action");
	private static final PseudoClass CANCEL_PSEUDO_CLASS = PseudoClass.getPseudoClass("cancel");

	public Button() {
		this(null);
	}

	public Button(String text) {
		if (text != null) {
			setText(text);
		}

		styledProperty().addListener(change -> {
			boolean styled = isStyled();

			// 기본 타입 지정
			setType(ButtonType.ACTION);

			if (styled) {
				setMinWidth(50);
				setMinHeight(20);
				// setPrefWidth(50);
				setPrefHeight(25);
			}

			pseudoClassStateChanged(STYLED_PSEUDO_CLASS, styled);
		});

		typeProperty().addListener(change -> {
			ButtonType type = getType();
		    pseudoClassStateChanged(ACTION_PSEUDO_CLASS, type.equals(ButtonType.ACTION));
			pseudoClassStateChanged(CANCEL_PSEUDO_CLASS, type.equals(ButtonType.CANCEL));
		});

		// 텍스트가 입력됐으면 여백 값 입력
		textProperty().addListener(change -> {
			if (getText() != null) {
				setPadding(new Insets(0, 10, 0, 10));
			}
		});

		// setType(Type.CANCEL);
	}

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
	public boolean isStyled() {
		return styledProperty.get();
	}
	public BooleanProperty styledProperty() {
		return styledProperty;
	}
}
