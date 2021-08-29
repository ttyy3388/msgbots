package org.beuwi.msgbots.program.gui.control;

import javafx.beans.property.ReadOnlyObjectProperty;

public class ComboBox<T> extends javafx.scene.control.ComboBox<T> {
	public ComboBox() {
		setPrefWidth(200);
		setPrefHeight(25);
		// getStyleClass().addAll("combo-box");
	}

	public void selectItem(T item) {
		getSelectionModel().select(item);
	}

	public T getSelectedItem() {
        return getSelectionModel().getSelectedItem();
    }

    public ReadOnlyObjectProperty<T> selectedItemProperty() {
		return getSelectionModel().selectedItemProperty();
	}
}
