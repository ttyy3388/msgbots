package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;

import org.beuwi.msgbots.view.gui.control.base.ButtonBase;
import org.beuwi.msgbots.view.gui.type.ButtonType;

public class Button extends ButtonBase {
	private final ObjectProperty<ButtonType> typeProperty = new SimpleObjectProperty(null);
	public final ObjectProperty<ButtonType> typeProperty() {
		return typeProperty;
	}
	public void setType(ButtonType type) {
		typeProperty.set(type);
	}
	public ButtonType getType() {
		return typeProperty.get();
	}

	private final BooleanProperty styledProperty = new SimpleBooleanProperty(false);
	public final BooleanProperty styledProperty() {
		return styledProperty;
	}
	public void setStyled(boolean styled) {
		styledProperty.set(styled);
	}
	public boolean isStyled() {
		return styledProperty.get();
	}

	public Button() {
		this(null);
	}

	public Button(String text) {
		if (text != null) {
			setText(text);
		}

		addChangeListener("styled", change -> {
			boolean styled = isStyled();

			// 기본 타입 지정
			setType(ButtonType.ACTION);

			if (styled) {
				setMinWidth(50);
				setMinHeight(20);
				// setPrefWidth(50);
				setPrefHeight(25);
			}

			setPseudoClass("styled", styled);
		});

		addChangeListener("type", change -> {
			ButtonType type = getType();
			setPseudoClass("action", type.equals(ButtonType.ACTION));
			setPseudoClass("cancel", type.equals(ButtonType.CANCEL));
		});

		// 텍스트가 입력됐으면 여백 값 입력
		addChangeListener("text", change -> {
			String value = getText();
			if (value != null) {
				setPadding(new Insets(0, 10, 0, 10));
			}
		});

		// setType(Type.CANCEL);
	}
}
