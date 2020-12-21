package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;

import java.util.Collection;
import java.util.List;

public class ListView<T> extends javafx.scene.control.ListView<T> {
	private static final String DEFAULT_STYLE_CLASS = "list-view";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	// private final BooleanProperty autoscroll = new SimpleBooleanProperty();

	public ListView() {
		selectedItemProperty().addListener((observable, oldItem, newItem) -> {
			if (newItem instanceof Node) {
				((Node) newItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
			}
			if (oldItem instanceof Node) {
				((Node) oldItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
		});

		getItems().addListener((ListChangeListener<T>) change  -> {
			while (change.next()) {
				for (T item : change.getAddedSubList()) {
					scrollTo(item);
				}
			}
		});

		// getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public T findItem(String id) {
		List<T> list = getItems();

		for (T item : list) {
			if (item instanceof Node) {
				if (((Node) item).getId().equals(id)) {
					return item;
				}
			}
		}

		return null;
	}

	/* public void setAutoScrollable(boolean value) {
		autoscroll.set(value);
	} */

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
