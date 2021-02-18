package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ListView<T> extends javafx.scene.control.ListView<T> {
	private static final String DEFAULT_STYLE_CLASS = "list-view";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private final BooleanProperty fitWidthProperty = new SimpleBooleanProperty();
	private final InvalidationListener fitWidthListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			for (T item : getItems()) {
				if (item instanceof Pane) {
					((Pane) item).setPrefWidth(getPrefWidth() / getItems().size());
				}
			};
		}
	};
	public void setFitWidth(boolean fittable) {
		fitWidthProperty.set(fittable);
	}
	public boolean isFitWidth() {
		return fitWidthProperty.get();
	}
	public BooleanProperty fitWidthProperty() {
		return fitWidthProperty;
	}

	// Enable Mouse Secondary Button Select
	private final BooleanProperty secBtnSelectProperty = new SimpleBooleanProperty();
	public boolean isSecBtnSelect() {
		return secBtnSelectProperty.get();
	}
	public void setSecBtnSelect(boolean value) {
		secBtnSelectProperty.set(value);
	}
	public BooleanProperty secBtnSelectProperty() {
		return secBtnSelectProperty;
	}


	/* private final BooleanProperty fitHeightProperty = new SimpleBooleanProperty();
	private final InvalidationListener fitHeightListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			prefWidthProperty().addListener(change -> {
				for (T item : getItems()) {
					Node node = (Node) item;
					node.prefHeight(getPrefHeight() / getItems().size());
				};
			});
		}
	};
	public void setFitHeight(boolean fittable) {
		fitWidthProperty.set(fittable);
	}
	public boolean isFitHeight() {
		return fitWidthProperty.get();
	}
	public BooleanProperty fitHeightProperty() {
		return fitHeightProperty;
	} */

	// 해당 프로펄티가 활성화일 때는 자동으로 추가된 아이템으로 스크롤함
	private final BooleanProperty autoScrollProperty = new SimpleBooleanProperty();
	/* private final InvalidationListener autoScrollListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {
			if (isAutoScroll()) {
				getItems().addListener((ListChangeListener<T>) change -> {
					while (change.next()) {
						for (T item : change.getAddedSubList()) {
							scrollTo(item);
						}
					}
				});
			}
		}
	}; */
	public void setAutoScroll(boolean value) {
		autoScrollProperty.set(value);
	}
	public boolean isAutoScroll() {
		return autoScrollProperty.get();
	}
	public BooleanProperty autoScrollProperty() {
		return autoScrollProperty;
	}

	// 해당 프로펄티가 활성화일 때는 선택된 아이템으로 스크롤함
	private final BooleanProperty pickScrollProperty = new SimpleBooleanProperty();
	public void setPickScroll(boolean value) {
		pickScrollProperty.set(value);
	}
	public boolean isPickScroll() {
		return pickScrollProperty.get();
	}
	public BooleanProperty pickScrollProperty() {
		return pickScrollProperty;
	}

	public ListView() {
		/* autoscrollProperty().addListener(change -> {
			if (isAutoScroll()) {
				autoscrollProperty().addListener(autoscrollListener);
			}
			else {
				autoscrollProperty().removeListener(autoscrollListener);
			}
		}); */

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

		getItems().addListener((ListChangeListener<T>) change -> {
			while (change.next()) {
				// for (T item : change.getRemoved()) { }
				for (T item : change.getAddedSubList()) {
					 if (isAutoScroll()) {
						 scrollTo(item);
					 }
				 }
			 }
		 });

		addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
			if (event.getButton().equals(MouseButton.SECONDARY)) {
				if (!isSecBtnSelect()) {
					/* ContextMenu contextMenu = getContextMenu();
					if (contextMenu != null) {
						contextMenu.show(this, event.getScreenX(), event.getScreenY());
					} */
					event.consume();
					return ;
				}
			}
		});

		// 셀이 선택되었을 경우 자동으로 하위 아이템이 선택된거로 인식하도록
		selectedItemProperty().addListener((observable, oldItem, newItem) -> {
			if (newItem instanceof Node) {
				((Node) newItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
			}
			if (oldItem instanceof Node) {
				((Node) oldItem).pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
		});
		selectedItemProperty().addListener(change -> {
			// 자동 스크롤 기능이 켜져있으면 선택된 아이템으로 이동
			if (isPickScroll()) {
				scrollTo(getSelectedItem());
			}
		});

		setSecBtnSelect(true);
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
