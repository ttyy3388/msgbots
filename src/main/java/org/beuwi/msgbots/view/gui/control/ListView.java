package org.beuwi.msgbots.view.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.view.gui.control.base.ListViewBase;

import java.util.List;

public class ListView<T> extends ListViewBase<T> {
	private final BooleanProperty fitWidthProperty = new SimpleBooleanProperty();
	public final ReadOnlyBooleanProperty fitWidthProperty() {
		return fitWidthProperty;
	}
	public void setFitWidth(boolean fitWidth) {
		fitWidthProperty.set(fitWidth);
	}
	public boolean isFitWidth() {
		return fitWidthProperty.get();
	}
	private final InvalidationListener fitWidthListener = observable -> {
		List<T> items = getItems();
		for (T item : items) {
			if (item instanceof Pane pane) {
				double width = getPrefWidth();
				pane.setPrefWidth(width / items.size()); // 너비를 아이템 갯수로 나눔
			}
		};
	};

	private final BooleanProperty fitHeightProperty = new SimpleBooleanProperty();
	public final ReadOnlyBooleanProperty fitHeightProperty() {
		return fitHeightProperty;
	}
	public void setFitHeight(boolean fitWidth) {
		fitHeightProperty.set(fitWidth);
	}
	public boolean isFitHeight() {
		return fitHeightProperty.get();
	}
	private final InvalidationListener fitHeightListener = observable -> {
		List<T> items = getItems();
		for (T item : items) {
			if (item instanceof Pane pane) {
				double height = getPrefHeight();
				pane.setPrefHeight(height / items.size()); // 높이를 아이템 갯수로 나눔
			}
		};
	};

	// 해당 프로펄티가 활성화일 때는 자동으로 추가된 아이템으로 스크롤함
	private final BooleanProperty autoScrollProperty = new SimpleBooleanProperty();
	private ReadOnlyBooleanProperty autoScrollProperty() {
		return autoScrollProperty;
	}
	public void setAutoScroll(boolean value) {
		autoScrollProperty.set(value);
	}
	public boolean isAutoScroll() {
		return autoScrollProperty.get();
	}

	/* public void setUseVbar(boolean value) {
	}
	public void setUseHbar(boolean value) {
	} */

	public ListView() {
		getItems().addListener((ListChangeListener<T>) change -> {
			while (change.next()) {
				for (T item : change.getAddedSubList()) {
					if (isAutoScroll()) {
						scrollTo(item);
					}
				}
			}
		});
		addChangeListener("fitWidth", change -> {
			if (isFitWidth()) {
				getItems().addListener(fitWidthListener);
				getFXProperty("prefWidth").addListener(fitWidthListener);
			}
			else {
				getItems().removeListener(fitWidthListener);
				getFXProperty("prefWidth").removeListener(fitWidthListener);
			}
		});

		addChangeListener("fitHeight", change -> {
			if (isFitHeight()) {
				getItems().addListener(fitHeightListener);
				getFXProperty("prefHeight").addListener(fitHeightListener);
			}
			else {
				getItems().removeListener(fitHeightListener);
				getFXProperty("prefHeight").removeListener(fitHeightListener);
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

		SelectionModel<T> model = getSelectionModel();
		// 셀이 선택되었을 경우 자동으로 하위 아이템이 선택된거로 인식하도록
		addChangeListener(getFXProperty(model, "selectedItem"), (ChangeListener<Node>) (observable, oldItem, newItem) -> {
			setPseudoClass(newItem, "selected", true);
			if (oldItem != null) {
				setPseudoClass(oldItem, "selected", false);
			}
		});
	}

	@Override
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

	public ObservableList<Integer> getSelectedIndices() {
		return getSelectionModel().getSelectedIndices();
	}
	public ObservableList<T> getSelectedItems() {
		return getSelectionModel().getSelectedItems();
	}
}
