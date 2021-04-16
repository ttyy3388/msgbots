package org.beuwi.msgbots.platform.gui.control.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.base.Control;
import org.beuwi.msgbots.platform.gui.control.ListView;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;

import java.util.*;

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
					headerArea.getItems().remove(item);
					// 선택된 탭이 제거됐다면 이전 탭이나 다음 탭 선택
					// 단 이럴 상황에서는 리스트 뷰에서 선택됐던 탭이 "New Tab"에서 "Old Tab"이 될 텐데
					// 새 탭을 선택하면 Old Tab[null], New Tab[Tab Item]과 같이 된다. ?
					if (item.isSelected()) {
						int index = getTabs().indexOf(item),
							size = getTabs().size();

						// 탭이 한개만 남았다면
						if (size == 1) {
							// 첫 번째 탭 선택
							selectTab(0);
						}
						// 탭 목록이 여러 개 라면
						else if (size > 1 && index != -1) {
							// 다음 탭이 있다면
							if (size > index + 1) {
								selectTab(index + 1);
							}
							// 이전 탭이 있다면
							else if (size > index) {
								selectTab(index - 1);
							}
						}
					}
				}
				for (T item : change.getAddedSubList()) {
					item.setView(this);
					headerArea.getItems().add(item);
					this.selectTab(item);
				}
			}

			// 비어있다면 탭 뷰가 안보이도록
			setVisible(!getTabs().isEmpty());
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

		/* headerArea.selectedItemProperty().addListener((observable, oldItem, newItem) -> {
			System.out.println("(OLD ITEM : " + oldItem + "), (NEW ITEM : " + newItem + "), (TAB LIST : " + getTabs() + ")");
		}); */

		// 선택된 탭을 리스트 뷰에서도 선택된거로 인식하도록
		selectedTabProperty().addListener(change -> {
			headerArea.selectItem(getSelectedTab());
		});
		selectedTabProperty().addListener(change -> {
			Node content = getSelectedTab().getContent();
			// 해당 탭에 대한 컨텐츠 변경 이벤트
			contentArea.getChildren().setAll(content);
			// 변경된 탭에 포커스가 가도록
			content.requestFocus();
		});
		// 해당 탭에 대한 선택됨 여부 변경
		selectedTabProperty().addListener((observable, oldTab, newTab) -> {
			if (oldTab != null) {
				// 기존 탭 선택 취소
				oldTab.selectedProperty().set(false);
			}
			// 새 탭 선택 처리
			newTab.selectedProperty().set(true);
		});
		// 해당 탭에 대한 CSS PSEUDO CLASS 변경 <- 리스트 뷰에서 처리하므로 주석 처리
		/* selectedTabProperty().addListener((observable, oldTab, newTab) -> {
			if (oldTab != null) {
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		}); */

		if (tabs != null) {
			addTab(tabs);
		}

		addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					case TAB :
						// Select Previous Tab
						if (event.isShiftDown()) {
							selectPrevTab();
						}
						// Select Next Tab
						else {
							selectNextTab();
						}
						break;
					// Close Current Tab
					case W :
						closeTab(getSelectedTab());
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
		headerArea.setSecBtnSelect(false); // 우측 마우스 클릭 비허용
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
		// setDraggable(true);
		// setMargin(DEFAULT_VIEW_MARGIN);
		setPrefWidth(DEFAULT_VIEW_WIDTH);
		setPrefHeight(DEFAULT_VIEW_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	/* public void setDraggable(boolean draggable) {
		if (draggable) {
			TabDragSupport support = new TabDragSupport(this);
		}
	} */

	private final ObservableList<T> tabList = FXCollections.observableArrayList();
	public ObservableList<T> getTabs() {
		return tabList;
	}
	public void addTab(int index, T tab) {
		boolean already = getTab(tab.getId()) != null;
		// 이미 있으면 해당 탭 선택
		if (already) {
			selectTab(tab);
		}
		else {
			getTabs().add(index, tab);
		}
	}
	public void addTab(Collection<T> tabs) {
		tabs.forEach(tab -> {
			addTab(tabList.size(), tab);
		});
	}
	public void addTab(T... tabs) {
		addTab(Arrays.asList(tabs));
	}
	/* public void addTab(T tab) {
		addTab(getTabs().size(), tab);
	} */
	/* private void addTabList(Collection<T> tabs) {
		tabs.forEach(tab -> {
			addTab(tabList.size(), tab);
		});
	} */

	public void setTab(T... tabs) {
		closeAllTabs();
		addTab(tabs);
	}

	public T getTab(int index) {
		if (index == -1) {
			return null;
		}
		return getTabs().get(index);
	}
	public T getTab(String id) {
		int index = findTab(id);
		if (index != -1) {
			return getTabs().get(index);
		}
		else {
			return null;
		}
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

	/* public void moveForward(T tab) {
		int index = getTabs().indexOf(tab);
		int size = getTabs().size();

		List<T> list = getTabs();

		// 만약 탭이 마지막 자리에 있다면 해당 값이 사이즈를 초과하므로 다음 탭이 있는 경우에만 통과됨
		// if (size >= index + 1) {
			T toMove = list.get(index);
			list.set(index, list.get(index + 1));
			list.set(index + 1, toMove);
		// }

		selectTab(tab);
	}
	public void moveBack(T tab) {
		int index = getTabs().indexOf(tab);
		int size = getTabs().size();

		System.out.println(size + " : " + index);
		// 만약 탭이 0번째 자리에 있다면 해당 값이 -1이므로 이전 탭이 있는 경우에만 통과됨
		if (index - 1 >= 0) {
			Collections.swap(getTabs(), index, index - 1);
		}
	} */

	// 단일 탭 제거
	public void closeTab(T tab) {
		// 해당 탭 제거
		getTabs().remove(tab);
	}
	// 다중 탭 제거
	// 타겟 탭을 기준으로 삭제 후 선택 탭이 결정됨 (우측 탭 닫기, 다른 탭 닫기 등...)
	public void closeTabList(Collection<T> tabs) {
		List<T> removeTabs = new ArrayList<>();
		for (T tab : tabs) {
			if (!tab.isClosable()) {
				return ;
			}
			removeTabs.add(tab);
		}
		getTabs().removeAll(removeTabs);

		// 현재는 탭이 남아있을 상황이 "다른 탭 닫기"밖에 없음
		// 따라서 탭이 남아있다면 0번째 탭 선택
		/* int size = getTabs().size();
		System.out.println(size);
		if (size == 1) {
			selectTab(0);
		} */
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

	/* public void moveToRightTab(T tab) {
		int index = getTabs().indexOf(tab),
			size = getTabs().size();

		List<T> list = getTabs();

		System.out.println(size + " : " + index);
		// 만약 탭이 마지막 자리에 있다면 해당 값이 사이즈를 초과하므로 다음 탭이 있는 경우에만 통과됨
		if (size >= index + 1) {
			Collections.swap(getTabs(), index, index + 1);
		}
	}

	public void moveToLeftTab(T tab) {
		int index = getTabs().indexOf(tab),
				size = getTabs().size();

		// 만약 탭이 0번째 자리에 있다면 해당 값이 -1이므로 이전 탭이 있는 경우에만 통과됨
		if (index - 1 >= 0) {
			Collections.swap(getTabs(), index, index - 1);
		}
	} */

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
	/* public void setSelectedTab(T tab) {
		selectedTabProperty.set(tab);
	} */
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
		selectTab(getTab(index));
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
			e.printStackTrace();
			selectTab(size - 1);
		}
	}

	/* private class TabDragSupport {
		// private final AtomicLong dragId = new AtomicLong();

		private final StringProperty dragId = new SimpleStringProperty();

		// Current Dragging Tab
		private T target;

		public TabDragSupport(TabViewBase<T> control) {
			control.getTabs().forEach(item -> addDragHandlers(item));
			control.getTabs().addListener((ListChangeListener<T>) change -> {
				while (change.next()) {
					if (change.wasAdded()) {
						change.getAddedSubList().forEach(this::addDragHandlers);
					}
					if (change.wasRemoved()) {
						change.getRemoved().forEach(this::removeDragHandler);
					}
				}
			});
		}

		private void addDragHandlers(T tab) {
			/* if (tab.getHeader() != null) {
				Label label = new Label();
				label.setGraphic(tab.getHeader());
			} */

			/* Node header = tab.getHeader();

			header.setOnDragDetected(event -> {
				Dragboard dragboard = header.startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(dragId.get());
				dragboard.setContent(content);
				dragboard.setDragView(header.snapshot(null, null));
				target = tab;
			});
			header.setOnDragOver(event -> {
				if (dragId.get().equals(event.getDragboard().getString())) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
			});
			/* header.setOnDragDropped(event -> {
				if (dragId.get().equals(event.getDragboard().getString())) {
					int index = getTabs().indexOf(tab);
					getTabs().add(index, target);
				}
			}); */
		/* }

		public void removeDragHandler(T tab) {
			Node header = tab.getHeader();
			header.setOnDragDetected(null);
			header.setOnDragOver(null);
			header.setOnDragDropped(null);
			header.setOnDragDone(null);
		}

		public StringProperty dragIdProperty() {
			return dragId;
		}
	} */
}
