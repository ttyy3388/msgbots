package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

public class ListView<T> extends javafx.scene.control.ListView<T> {
	private static final String DEFAULT_STYLE_CLASS = "list-view";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	public ListView() {
		selectedItemProperty().addListener((observable, oldItem, newItem) -> {
			if (newItem instanceof Node) {
				((Node) newItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
			}
			if (oldItem instanceof Node) {
				((Node) oldItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
		});

		// getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public void setSelectionMode(SelectionMode mode) {
		getSelectionModel().setSelectionMode(mode);
	}

	public T getSelectedItem() {
		return getSelectionModel().getSelectedItem();
	}

	public ObservableList<T> getSelectedItems() {
		return getSelectionModel().getSelectedItems();
	}

	public ReadOnlyObjectProperty<T> selectedItemProperty() {
		return getSelectionModel().selectedItemProperty();
	}
}
