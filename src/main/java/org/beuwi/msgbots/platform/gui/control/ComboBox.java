package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ReadOnlyObjectProperty;

public class ComboBox<T> extends javafx.scene.control.ComboBox<T> {

	private static final String DEFAULT_STYLE_CLASS = "combo-box";

	private static final int DEFAULT_PREF_WIDTH = 200;
	private static final int DEFAULT_PREF_HEIGHT = 25;

	public ComboBox()
	{
		setPrefWidth(DEFAULT_PREF_WIDTH);
		setPrefHeight(DEFAULT_PREF_HEIGHT);

		// addStyleClass(DEFAULT_STYLE_CLASS);
	}

	public void select(T item)
	{
		getSelectionModel().select(item);
	}

	public T getSelectedItem()
    {
        return getSelectionModel().getSelectedItem();
    }

	public ReadOnlyObjectProperty<T> getSelectedItemProperty()
    {
        return getSelectionModel().selectedItemProperty();
    }
}
