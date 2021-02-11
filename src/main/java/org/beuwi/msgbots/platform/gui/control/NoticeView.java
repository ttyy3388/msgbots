package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.beuwi.msgbots.platform.gui.layout.VBox;

// 원래는 리스트 뷰를 사용하는게 맞으나 높이 문제로 인해 "VBOX" 사용
public class NoticeView extends VBox<NoticeItem> {
	private static final String DEFAULT_STYLE_CLASS = "notice-view";

	private static final int DEFAULT_PREF_WIDTH = 500;
	private static final int DEFAULT_MIN_HEIGHT = 50;
	private static final int DEFAULT_PREF_HEIGHT = 0;
	private static final double DEFAULT_MAX_HEIGHT = Double.MAX_VALUE;
	private static final int DEFAULT_GAP_VALUE = 10;

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private final ObjectProperty<NoticeItem> selectedItemProperty = new SimpleObjectProperty(null);

	public NoticeView() {
		getItems().addListener((ListChangeListener<NoticeItem>) change -> {
			for (NoticeItem toast : getItems()) {
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

		// setSpacing(DEFAULT_GAP_VALUE);
		setPrefWidth(DEFAULT_PREF_WIDTH);
		// setMinHeight(DEFAULT_MIN_HEIGHT);
		setMaxHeight(DEFAULT_MAX_HEIGHT);
		// setPrefHeight(DEFAULT_PREF_HEIGHT);

	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public ObservableList<NoticeItem> getItems() {
		return (ObservableList) getChildren();
	}

	public void selectItem(NoticeItem item) {
		setSelectedItem(item);
	}
	public void setSelectedItem(NoticeItem item) {
		selectedItemProperty.set(item);
	}
	public NoticeItem getSelectedItem() {
		return selectedItemProperty.get();
	}
	public ObjectProperty<NoticeItem> selectedItemProperty() {
		return selectedItemProperty;
	}
}