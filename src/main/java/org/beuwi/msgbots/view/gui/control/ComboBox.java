package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;

import org.beuwi.msgbots.view.gui.control.base.ComboBoxBase;

public class ComboBox<T> extends ComboBoxBase<T> {
	public ComboBox() {
		setMinHeight(25);
		setPrefWidth(200);
		setPrefHeight(25);
		// addStyleClass("combo-box");
	}

	public void selectNext() {
		getSelectionModel().selectNext();
	}
	public void selectPrevious() {
		getSelectionModel().selectPrevious();
	}
	public void selectItem(T item) {
		getSelectionModel().select(item);
	}
	public void setSelectedItem(T item) {
		getSelectionModel().select(item);
	}
	public T getSelectedItem() {
		return getSelectionModel().getSelectedItem();
	}
	public int getSelectedIndex() {
		return getSelectionModel().getSelectedIndex();
	}
	public ReadOnlyObjectProperty<T> selectedItemProperty() {
		return getSelectionModel().selectedItemProperty();
	}
	public ReadOnlyIntegerProperty selectedIndexProperty() {
		return getSelectionModel().selectedIndexProperty();
	}
}
