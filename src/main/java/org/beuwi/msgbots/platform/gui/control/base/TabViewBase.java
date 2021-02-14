package org.beuwi.msgbots.platform.gui.control.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.base.Control;
import org.beuwi.msgbots.platform.gui.control.ListView;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class TabViewBase<T extends TabItemBase> extends StackPane implements Control {
	private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private static final String HEADER_STYLE_CLASS = "tab-header-area";
	private static final String CONTENT_STYLE_CLASS = "tab-content-area";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	// private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");
	// public static final PseudoClass NORMAL_PSEUDO_CLASS = PseudoClass.getPseudoClass("normal");
	// public static final PseudoClass SYSTEM_PSEUDO_CLASS = PseudoClass.getPseudoClass("system");

	private final static Side DEFAULT_SIDE_VALUE = Side.TOP;

	private final static int DEFAULT_VIEW_WIDTH = 500;
	private final static int DEFAULT_VIEW_HEIGHT = 600;

	private final ListView<T> headerArea = new ListView();
	public final ListView getHeaderArea() {
		return headerArea;
	}

	private final StackPane contentArea = new StackPane();
	public final StackPane getContentArea() {
		return contentArea;
	}

	{
		HBox.setHgrow(contentArea, Priority.ALWAYS);
		VBox.setVgrow(contentArea, Priority.ALWAYS);
	}

	public TabViewBase() {
		this(null);
	}
	public TabViewBase(T... tabs) {
		getTabs().addListener((ListChangeListener<T>) change -> {
			while (change.next()) {
				for (T item : change.getRemoved()) {
					item.setView(null);
					// this.selectTab(null);
					headerArea.getItems().remove(item);
				}
				for (T item : change.getAddedSubList()) {
					item.setView(this);
					this.selectTab(item);
					headerArea.getItems().add(item);
				}

				// 비어있다면 안보이도록
				setVisible(getTabs().size() != 0);
			}
		});

		sideProperty().addListener(change -> {
			Side side = getSide();
			Pane root = null;

			// Left or right
			if (side.isVertical()) {
				headerArea.setOrientation(Orientation.VERTICAL);
			}
			else {
				headerArea.setOrientation(Orientation.HORIZONTAL);
			}

			switch (side) {
				case TOP : root = new VBox(headerArea, contentArea); break;
				case BOTTOM : root = new VBox(contentArea, headerArea); break;
				case LEFT : root = new HBox(headerArea, contentArea); break;
				case RIGHT : root = new HBox(contentArea, headerArea); break;
			};

			getChildren().setAll(root);
		});

		// 선택된 탭을 리스트 뷰에서도 선택된거로 인식되도록
		selectedTabProperty().addListener(change -> {
			headerArea.setSelectedItem(getSelectedTab());
		});
		// 해당 탭에 대한 컨텐츠 변경 이벤트
		selectedTabProperty().addListener(change -> {
			contentArea.getChildren().setAll(getSelectedTab().getContent());
		});
		// 해당 탭에 대한 선택됨 여부 변경
		selectedTabProperty().addListener((observable, oldTab, newTab) -> {
			if (oldTab != null) {
				oldTab.selectedProperty().set(false);
			}
			newTab.selectedProperty().set(false);
		});
		// 해당 탭에 대한 CSS PSEUDO CLASS 변경
		selectedTabProperty().addListener((observable, oldTab, newTab) -> {
			if (oldTab != null) {
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		if (tabs != null) {
			addTab(tabs);
		}

		addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					case TAB :
						if (event.isShiftDown()) {
							selectPrevTab();
						}
						else {
							selectNextTab();
						}
						break;
				}
			}
		});

		// Use computed size
		// headerArea.setMinWidth(0);
		// headerArea.setMinHeight(0);
		// headerArea.setPrefWidth(0);
		// headerArea.setPrefHeight(0);
		// headerArea.setMaxWidth(0);
		// headerArea.setMaxHeight(0);
		headerArea.setPickScroll(true);
		headerArea.setAutoScroll(true);
		/* headerArea.setOrientation(Orientation.HORIZONTAL);
		getChildren().setAll(new VBox(
			headerArea,
			contentArea
		)); */
		/* headerArea.selectedItemProperty().addListener(change -> {
			headerArea.scrollTo(headerArea.getSelectedItem());
		}); */
		headerArea.getStyleClass().add(HEADER_STYLE_CLASS);

		contentArea.getStyleClass().add(CONTENT_STYLE_CLASS);

		setSide(DEFAULT_SIDE_VALUE); // Default
		setVisible(false);
		// setMargin(DEFAULT_VIEW_MARGIN);
		setPrefWidth(DEFAULT_VIEW_WIDTH);
		setPrefHeight(DEFAULT_VIEW_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	private final ObservableList<T> tabList = FXCollections.observableArrayList();
	public ObservableList<T> getTabs() {
		return tabList;
	}
	public void addTab(T tab) {
		if (getTabs().contains(tab)) {
			selectTab(tab);
		}
		else {
			getTabs().add(tab);
		}
	}
	/* public void addTab(T tab) {
		addTab(getTabs().size(), tab);
	} */
	public void addTab(T... tabs) {
		for (T tab : tabs) {
			addTab(tab);
		}
	}

	public void setTab(T... tabs) {
		closeAllTabs();
		addTab(tabs);
	}

	public T getTab(int index) {
		return getTabs().get(index);
	}
	public T getTab(String id) {
		int index = findTab(id);
		if (index != -1) {
			return getTabs().get(index);
		}

		return null;
	}
	private int findTab(String id) {
		List<T> items = getTabs();

		for (int index = 0 ; index < items.size() ; index ++) {
			if (items.get(index).getId().equals(id)) {
				return index;
			}
		}

		return -1;
	}

	public void closeTabList(Collection<T> tabs) {
		List<T> targets = new ArrayList<>();
		for (T tab : tabs) {
			if (!tab.isClosable()) {
				return ;
			}

			int index = getTabs().indexOf(tab),
					size = getTabs().size();

			if (size > 1 && index != -1) {
				// If have a next tab
				if (size > index + 1) {
					selectTab(index + 1);
				}
				// If have a previous tab
				else if (size > index) {
					selectTab(index - 1);
				}
			}

			targets.add(tab);
		}

		getTabs().removeAll(targets);
	}
	public void closeTab(T... tabs) {
		closeTabList(Arrays.asList(tabs));
	}
	public void closeTab(String id) {
		closeTab(getTab(id));
	}
	public void closeAllTabs() {
		closeTabList(getTabs());
	}
	public void closeOtherTabs(T tab) {
		List<T> others = new ArrayList<>();

		for (T item : getTabs()) {
			if (tab.equals(item)) {
				continue;
			}
			others.add(item);
		}

		closeTabList(others);
	}

	private final ObjectProperty<Side> sideProperty = new SimpleObjectProperty();
	public void setSide(Side side) {
		sideProperty.set(side);
	}
	public Side getSide() {
		return sideProperty.get();
	}
	public ObjectProperty<Side> sideProperty() {
		return sideProperty;
	}

	private final ObjectProperty<T> selectedTabProperty = new SimpleObjectProperty(null);
	public void setSelectedTab(T tab) {
		selectedTabProperty.set(tab);
	}
	public T getSelectedTab() {
		return selectedTabProperty.get();
	}
	public ObjectProperty<T> selectedTabProperty() {
		return selectedTabProperty;
	}
	public void selectTab(T tab) {
		selectedTabProperty.set(tab);
	}
	public void selectTab(int index) {
		selectedTabProperty.set(getTab(index));
	}
	public void selectNextTab() {
		selectNextTab(getSelectedTab());
	}
	public void selectNextTab(T tab) {
		int index = getTabs().indexOf(tab),
				size = getTabs().size();

		// 탭이 없을 경우
		if (size < 1 || index < 0) {
			return ;
		}

		// 다음 탭이 있다면
		if (size > index + 1) {
			selectTab(index + 1);
		}
		// 없으면 첫번째 탭 선택
		else {
			selectTab(0);
		}
	}
	public void selectPrevTab() {
		selectPrevTab(getSelectedTab());
	}
	public void selectPrevTab(T tab) {
		int index = getTabs().indexOf(tab),
				size = getTabs().size();

		// 탭이 없을 경우
		if (size < 1 || index < 0) {
			return ;
		}

		// 다음 탭이 있다면
		try {
			selectTab(index - 1);
		}
		// 만약 0번째 탭에서 이전탭으로 이동하려고 한다면 에러가 발생하기에 편법을 사용해서 다음 탭으로 이동
		catch (IndexOutOfBoundsException e) {
			selectTab(size - 1);
		}
	}
}
