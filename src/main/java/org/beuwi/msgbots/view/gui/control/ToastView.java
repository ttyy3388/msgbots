package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;

import org.beuwi.msgbots.view.gui.layout.VBox;

// 원래는 리스트 뷰를 사용하는게 맞으나 높이 문제로 인해 "VBox" 사용
public class ToastView extends VBox {
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private final ObjectProperty<ToastItem> selectedItemProperty = new SimpleObjectProperty(null);

	public ToastView() {
		getItems().addListener((ListChangeListener<ToastItem>) change -> {
			for (ToastItem toast : getItems()) {
				toast.setView(this);
			}

			// 아이템이 없으면 안보이도록
			// setVisible(!getChildren().isEmpty());
		});

		selectedItemProperty().addListener((observable, oldItem, newItem) -> {
			if (oldItem != null) {
				oldItem.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
			if (newItem != null) {
				newItem.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
			}
		});

		// setVisible(false);
		// setSpacing(10);
		setPrefWidth(500);
		// setMinHeight(50);
		setMaxHeight(Double.MAX_VALUE);
		// setPrefHeight(500);

	    getStyleClass().add("toast-view");
	}

	// ListView처럼 동작해야 하므로 getItems로 재선언
	public ObservableList<ToastItem> getItems() {
		return (ObservableList) getChildren();
	}

	public void selectItem(ToastItem item) {
		setSelectedItem(item);
	}
	public void setSelectedItem(ToastItem item) {
		selectedItemProperty.set(item);
	}
	public ToastItem getSelectedItem() {
		return selectedItemProperty.get();
	}
	public ObjectProperty<ToastItem> selectedItemProperty() {
		return selectedItemProperty;
	}
}