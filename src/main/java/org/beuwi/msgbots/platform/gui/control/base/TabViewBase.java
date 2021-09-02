package org.beuwi.msgbots.platform.gui.control.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.control.ListView;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class TabViewBase<T extends TabItemBase> extends StackPane {
	// private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	// private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");
	// public static final PseudoClass NORMAL_PSEUDO_CLASS = PseudoClass.getPseudoClass("normal");
	// public static final PseudoClass SYSTEM_PSEUDO_CLASS = PseudoClass.getPseudoClass("system");

	private final ListView<T> headerArea = new ListView();
	public ListView<T> getHeaderArea() {
		return headerArea;
	}

	private final StackPane contentArea = new StackPane();
	public StackPane getContentArea() {
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
		// 추후 추가된 탭 아이템의 헤더 높이나 길이에 따라
		// 헤더 영역이 유동적으로 변하는 코드를 작성해야 할 필요가 있을 거 같음
		// 테두리 지정이나 모종의 이유로 헤더 높이가 변할 가능성이 있을 거 같음
		getTabs().addListener((ListChangeListener<T>) change -> {
			while (change.next()) {
				// 탭 추가 시
				for (T item : change.getAddedSubList()) {
					item.setView(this);
					headerArea.getItems().add(item);
					selectTab(item);
				}
				// 탭 제거 시
				for (T item : change.getRemoved()) {
					item.setView(null);
					// 마지막으로 헤더에서도 제거
					headerArea.getItems().remove(item);
				}
			}

			initStyleClass();
			// 비어있다면 탭 뷰가 안보이도록
			setVisible(!getTabs().isEmpty());
		});

		sideProperty().addListener(change -> {
			Side side = getSide();
			Pane inner = null;

			if (side.isVertical()) {
				headerArea.setOrientation(Orientation.VERTICAL); // Left or right
			}
			else {
				headerArea.setOrientation(Orientation.HORIZONTAL); // Top or Bottom
			}

			switch (side) {
				case TOP : inner = new VBox(headerArea, contentArea); break;
				case BOTTOM : inner = new VBox(contentArea, headerArea); break;
				case LEFT : inner = new HBox(headerArea, contentArea); break;
				case RIGHT : inner = new HBox(contentArea, headerArea); break;
			};

			initStyleClass();
			getChildren().setAll(inner);
		});

		// 탭 선택 시 해당 탭에 대한 작업
		selectedTabProperty().addListener((observable, oldTab, newTab) -> {
			// 선택된 탭이 없다면, 모든 탭이 제거된 거로 인식
			if (newTab == null) {
				headerArea.getItems().clear();
				contentArea.getChildren().clear();
				return ;
			}
			if (oldTab != null) {
				oldTab.setSelected(false); // 기존 탭 선택 취소
			}
			newTab.setSelected(true); // 새 탭 선택 처리

			// 탭 선택 시 컨텐츠 변경 및 포커스가 가도록
			Node content = newTab.getContent();
			contentArea.getChildren().setAll(content); // 해당 탭에 대한 컨텐츠 변경 이벤트
			content.requestFocus(); // 해당 탭의 Content에 포커스

			// System.out.println(oldTab + " : " + newTab + " : " + newTab.getContent());

			// 탭 선택 시 해당 탭을 리스트 뷰에서도 선택된거로 인식하도록
			headerArea.selectItem(newTab);
		});

		if (tabs != null) {

		}

		headerArea.setAutoScroll(true);

		headerArea.getStyleClass().add("header-area");
		contentArea.getStyleClass().add("content-area");

		setSide(Side.TOP); // Default
		setVisible(false);
		// setDraggable(true);
		// setMargin(DEFAULT_VIEW_MARGIN);
		setPrefWidth(500);
		setPrefHeight(600);
	}

	// 원래는 루트여야 하나, 탭이 회전함에따라 VBox, HBox가 루트가 아닌 StackPane가 루트가 됨
	// 따라서 StackPane의 기본 스타일 클래스를 제외한 나머지를 VBox, HBox에 넘기도록 함
	// 이렇게 하는 이유는 직계 자손때문(tab-view > * > header-area)로도 접근이 가능하나, *를 지양하므로 (tab-view > header-area)로 접근하기 위해서임
	private void initStyleClass() {
		List<String> styles = getStyleClass();
		/* styles.forEach(style -> {
			if (!style.equals("stack-pane")) { }
		}); */
		// 0: stack-pane, 1: custom-class(tab-view or navi-view...)
		if (styles.size() > 1) {
			String style = styles.get(1);
			List<Node> children = getChildren();
			if (children.size() > 0) {
				children.get(0).getStyleClass().add(style);
			}
		}
	}

	private final ObservableList<T> tabList = FXCollections.observableArrayList();
	// addTab, setTab, removeTab 등 함수를 통해서만 리스트 접근 하는걸 권장함
	// unmodifiable로 반환하면, fxml에서 생성 시 getTabs를 참고해야하는데 에러나므로 보류
	public ObservableList<T> getTabs() {
		return tabList;
	}
	public void addTab(T tab) {
		addTab(getTabs().size(), tab);
	}
	public void addTab(int index, T tab) {
		boolean already = getTab(tab.getId()) != null;
		// 이미 추가 돼 있으면 해당 탭 선택
		if (already) {
			selectTab(tab);
		}
		else {
			getTabs().add(index, tab);
		}
	}
	public void addTab(T... tabs) {
		addTab(Arrays.asList(tabs));
	}
	public void addTab(Collection<T> tabs) {
		tabs.forEach(this::addTab);
	}

	// ConcurrentModificationException를 방지하고자 리스트는 리스트로 변환해서 처리함
	// 다중 탭을 삭제할 경우에는 선택 이벤트 필요없이 목록만 제거하고
	// 단일 탭을 제거할 경우에는 아래와 같이 선택 부분에 대한 처리를 진행함
	public void closeTab(T tab) {
		if (!tab.isClosable()) {
			return ;
		}

		List<T> list = getTabs();
		// 삭제한 탭이 선택 중이었던 탭이라면 삭제한 탭 기준으로 이전 탭, 다음 탭 중 하나를 해줘야 함
		if (tab.isSelected()) {
			int index = list.indexOf(tab);
			if (index == -1) {
				return;
			}
			// 인덱스 값을 구했으면 목록에서 제거
			list.remove(tab);
			// 삭제한 후 목록의 사이즈 계산
			int size = list.size();
			// 남아있는 탭이 한개도 없다면
			if (size == 0) {
				selectTab(null); // 선택된 탭이 없도록
			}
			// 탭이 한개만 남았다면
			else if (size == 1) {
				selectTab(0); // 첫 번째 탭 선택
			}
			else {
				// 삭제한 탭이 첫번째 였다면
				if (index == 0) {
					selectTab(0); // 그대로 첫번째 탭 선택
				}
				else {
					selectTab(index - 1); // 이전 탭 선택
				}
			}
		}
		else {
			// 해당 사항이 없으면 제거만
			list.remove(tab);
		}
	}
	public void closeCurrentTab() {
		closeTab(getSelectedTab());
	}

	// ConcurrentModificationException를 방지하고자 리스트는 리스트로 변환해서 처리함
	public void closeTab(T... tabs) {
		closeTabList(Arrays.asList(tabs));
	}
	public void closeTab(Collection<T> tabs) {
		closeTabList(tabs);
	}
	// [tab]을 제외한 나머지 탭들을 제거함
	public void closeOtherTabs(T tab) {
		getTabs().removeIf(other ->
			(tab != other) &&
			(other.isClosable())
		);
	}
	public void closeAllTabs() {
		selectTab(null);
		getTabs().removeIf(T::isClosable);
	}
	private void closeTabList(Collection<T> tabs) {
		tabs.removeIf(T::isClosable);
	}

	public void selectTab(T tab) {
		setSelectedTab(tab);
	}
	public void selectTab(int index) {
		selectTab(getTab(index));
	}
	public void selectNextTab(T tab) {
		selectTab(getTabs().indexOf(tab) + 1);
	}
	public void selectPrevTab(T tab) {
		selectTab(getTabs().indexOf(tab) - 1);
	}

	public T getTab(int index) {
		if (index == -1) {
			return null;
		}
		return getTabs().get(index);
	}
	public T getTab(String id) {
		int index = findById(id);
		if (index != -1) {
			return getTabs().get(index);
		}
		else {
			return null;
		}
	}

	public int findById(String id) {
		List<T> tabs = getTabs();
		for (int index = 0 ; index < tabs.size() ; index ++) {
			if (tabs.get(index).getId().equals(id)) {
				return index;
			}
		}
		return -1;
	}

	private final ObjectProperty<Side> sideProperty = new SimpleObjectProperty<Side>();
	public void setSide(Side side) {
		sideProperty.set(side);
	}
	public Side getSide() {
		return sideProperty.get();
	}
	public ReadOnlyObjectProperty<Side> sideProperty() {
		return sideProperty;
	}

	private final ObjectProperty<T> selectedTabProperty = new SimpleObjectProperty<T>(null);
	public void setSelectedTab(T tab) {
		selectedTabProperty.set(tab);
	}
	public T getSelectedTab() {
		return selectedTabProperty.get();
	}
	public ReadOnlyObjectProperty<T> selectedTabProperty() {
		return selectedTabProperty;
	}
}
