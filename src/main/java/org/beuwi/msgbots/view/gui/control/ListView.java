package org.beuwi.msgbots.view.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.List;

public class ListView<T> extends javafx.scene.control.ListView<T> {
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private final BooleanProperty fitWidthProperty = new SimpleBooleanProperty();
	private final InvalidationListener fitWidthListener = observable -> {
		List<T> items = getItems();
		for (T item : items) {
			if (item instanceof Pane) {
				double width = getPrefWidth();
				Pane pane = (Pane) item;
				pane.setPrefWidth(width / items.size()); // 너비를 아이템 갯수로 나눔
			}
		};
	};
	public void setFitWidth(boolean fitWidth) {
		fitWidthProperty.set(fitWidth);
	}
	public boolean isFitWidth() {
		return fitWidthProperty.get();
	}
	public ReadOnlyBooleanProperty fitWidthProperty() {
		return fitWidthProperty;
	}

	private final BooleanProperty fitHeightProperty = new SimpleBooleanProperty();
	private final InvalidationListener fitHeightListener = observable -> {
		List<T> items = getItems();
		for (T item : items) {
			if (item instanceof Pane) {
				double height = getPrefHeight();
				Pane pane = (Pane) item;
				pane.setPrefHeight(height / items.size()); // 높이를 아이템 갯수로 나눔
			}
		};
	};
	public void setFitHeight(boolean fitWidth) {
		fitHeightProperty.set(fitWidth);
	}
	public boolean isFitHeight() {
		return fitHeightProperty.get();
	}
	public ReadOnlyBooleanProperty fitHeightProperty() {
		return fitHeightProperty;
	}

	// 해당 프로펄티가 활성화일 때는 자동으로 추가된 아이템으로 스크롤함
	private final BooleanProperty autoScrollProperty = new SimpleBooleanProperty();
	public void setAutoScroll(boolean value) {
		autoScrollProperty.set(value);
	}
	public boolean isAutoScroll() {
		return autoScrollProperty.get();
	}
	public ReadOnlyBooleanProperty autoScrollProperty() {
		return autoScrollProperty;
	}

	/* public void setUseVbar(boolean value) {
	}
	public void setUseHbar(boolean value) {
	} */

	public ListView() {
		fitWidthProperty().addListener(change -> {
			if (isFitWidth()) {
				getItems().addListener(fitWidthListener);
				prefWidthProperty().addListener(fitWidthListener);
			}
			else {
				getItems().removeListener(fitWidthListener);
				prefWidthProperty().removeListener(fitWidthListener);
			}
		});

		fitHeightProperty().addListener(change -> {
			if (isFitHeight()) {
				getItems().addListener(fitHeightListener);
				prefWidthProperty().addListener(fitHeightListener);
			}
			else {
				getItems().removeListener(fitHeightListener);
				prefWidthProperty().removeListener(fitHeightListener);
			}
		});

		// 우측 버튼으로 선택되지 않도록 필터 지정
		addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
			MouseButton button = event.getButton();
			if (button.equals(MouseButton.SECONDARY)) {
				event.consume();
				return ;
			}
		});

		getItems().addListener((ListChangeListener<T>) change -> {
			while (change.next()) {
				for (T item : change.getAddedSubList()) {
					if (isAutoScroll()) {
						scrollTo(item);
					}
				}
			}
		});

		SelectionModel<T> model = getSelectionModel();
		// 셀이 선택되었을 경우 자동으로 하위 아이템이 선택된거로 인식하도록
		model.selectedItemProperty().addListener((observable, oldItem, newItem) -> {
			if (newItem instanceof Node) {
				((Node) newItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
			}
			if (oldItem instanceof Node) {
				((Node) oldItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
		});
	}

	public Node findById(String id) {
		for (T item : getItems()) {
			if (item instanceof Node) {
				Node node = (Node) item;
				if (node.getId().equals(id)) {
					return node;
				}
			}
		}
		return null;
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
	public void selectAll() {
		getSelectionModel().selectAll();
	}
	public void setSelectedItem(T item) {
		getSelectionModel().select(item);
	}
	public void setSelectionMode(SelectionMode mode) {
		getSelectionModel().setSelectionMode(mode);
	}
	public T getSelectedItem() {
		return getSelectionModel().getSelectedItem();
	}
	public int getSelectedIndex() {
		return getSelectionModel().getSelectedIndex();
	}
	public ObservableList<T> getSelectedItems() {
		return getSelectionModel().getSelectedItems();
	}
	public ReadOnlyObjectProperty<T> selectedItemProperty() {
		return getSelectionModel().selectedItemProperty();
	}
	public ReadOnlyIntegerProperty selectedIndexProperty() {
		return getSelectionModel().selectedIndexProperty();
	}
}
