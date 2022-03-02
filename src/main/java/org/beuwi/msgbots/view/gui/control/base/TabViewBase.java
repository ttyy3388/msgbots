package org.beuwi.msgbots.view.gui.control.base;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.view.gui.control.ListView;
import org.beuwi.msgbots.view.gui.layout.HBox;
import org.beuwi.msgbots.view.gui.layout.StackPane;
import org.beuwi.msgbots.view.gui.layout.VBox;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class TabViewBase<T extends TabItemBase> extends StackPane {
	private final ListView<T> headerArea = new ListView(); // Header Area
	private final StackPane contentArea = new StackPane(); // Content Area
	// private Pane innerArea = null;

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
					headerArea.addItem(item);
					selectTab(item);
				}
				// 탭 제거 시
				for (T item : change.getRemoved()) {
					item.setView(null);
					// 마지막으로 헤더에서도 제거
					headerArea.removeItem(item);
				}
			}

			// 비어있다면 탭 뷰가 안보이도록
			setVisible(!getTabs().isEmpty());
		});

		addChangeListener("side", change -> {
			Side side = getSide();
			Pane inner = null;

			if (side.isVertical()) {
				headerArea.setOrientation(Orientation.VERTICAL); // Left or right
			}
			else {
				headerArea.setOrientation(Orientation.HORIZONTAL); // Top or Bottom
			}

			inner = switch (side) {
				case TOP -> new VBox(headerArea, contentArea);
				case BOTTOM -> new VBox(contentArea, headerArea);
				case LEFT -> new HBox(headerArea, contentArea);
				case RIGHT -> new HBox(contentArea, headerArea);
			};

			initChildren(inner);
			addStyleClass(inner, "inner-area");
		});

		// 탭 선택 시 해당 탭에 대한 작업
		addChangeListener("selectedTab", (ChangeListener<T>) (observable, oldTab, newTab) -> {
			// 선택된 탭이 없다면, 모든 탭이 제거된 거로 인식
			if (newTab == null) {
				headerArea.initItems();
				contentArea.initChildren();
				return ;
			}

			// 탭 선택 시 컨텐츠 변경 및 포커스가 가도록
			Node content = newTab.getContent();
			contentArea.initChildren(content); // 이후 해당 탭에 대한 컨텐츠 변경 이벤트
			content.requestFocus(); // 해당 탭의 Content에 포커스

			// 탭 선택 시 해당 탭을 리스트 뷰에서도 선택된거로 인식하도록
			headerArea.selectItem(newTab);
		});

		headerArea.setAutoScroll(true);
		headerArea.addStyleClass("header-area");
		contentArea.addStyleClass("content-area");

		setSide(Side.TOP); // Default
		setVisible(false);
		setPrefWidth(500);
		setPrefHeight(600);
	}

	/* // 원래는 루트여야 하나, 탭이 회전함에따라 [VBox], [HBox]가 루트가 아닌 [StackPane]가 루트가 됨
	// 따라서 [StackPane]의 기본 스타일 클래스를 제외한 나머지를 [VBox], [HBox]에 넘기도록 함
	// 이렇게 하는 이유는 직계 자손때문(tab-view > * > header-area)로도 접근이 가능하나, (*)를 지양하므로 (tab-view > header-area)로 접근하기 위해서임
	private void loadStyleClass() {
		List<String> styles = getStyleClass();
		// 0: stack-pane, 1: custom-class(tab-view or navi-view...)
		if (styles.size() > 1) {
			Pane inner = getInnerArea();
			addStyleClass(inner, styles.get(1));
		}
	} */

	private final ObservableList<T> tabList = FXCollections.observableArrayList();
	public ObservableList<T> getTabs() {
		return tabList;
	}

	public void addTab(T tab) {
		List<T> list = getTabs();
		addTab(list.size(), tab);
	}
	public void addTab(int index, T tab) {
		boolean already = containsTab(tab);
		// 이미 추가 돼 있으면 해당 탭 선택
		if (already) {
			selectTab(tab);
		}
		else {
			List<T> list = getTabs();
			list.add(index, tab);
		}
	}
	public void addTab(T... tabs) {
		addTab(Arrays.asList(tabs));
	}
	public void addTab(Collection<T> tabs) {
		tabs.forEach(this::addTab);
	}

	public T getTab(int index) {
		if (index == -1) {
			return null;
		}
		return getTabs().get(index);
	}
	public T getTab(String id) {
		int index = findIndex(id);
		if (index != -1) {
			return getTabs().get(index);
		}
		else {
			return null;
		}
	}

	public void closeTab(T tab) {
		if (!tab.isClosable()) {
			return ;
		}

		List<T> list = getTabs();

		// 타겟이 선택 중이라면 삭제한 탭 기준으로 이전 탭, 다음 탭 중 하나를 선택함
		if (tab.isSelected()) {
			int index = list.indexOf(tab);
			if (index == -1) {
				return;
			}
			list.remove(tab); // 인덱스 값을 구했으면 목록에서 제거

			int size = list.size(); // 삭제한 후 목록의 사이즈 계산

			if (size == 0) { // 남아있는 탭이 한개도 없다면
				selectTab(null); // 선택된 탭이 없도록
			}
			else if (size == 1) { // 탭이 한개만 남았다면
				selectTab(0); // 첫 번째 탭 선택
			}
			else { // 탭이 여러개 남았다면
				if (index == 0) {  // 삭제한 탭이 첫번째 였다면
					selectTab(0); // 그대로 첫번째 탭 선택
				}
				else {
					selectTab(index - 1); // 이전 탭 선택
				}
			}
		}
		else {
			list.remove(tab); // 해당 사항이 없으면 제거만
		}
	}

	public void selectTab(T tab) {
		setSelectedTab(tab);
	}
	public void selectTab(int index) {
		selectTab(getTab(index));
	}

	public boolean containsTab(String id) {
		return containsTab(getTab(id));
	}
	public boolean containsTab(T tab) {
		List<T> tabs = getTabs();
		return tabs.contains(tab);
	}

	public int findIndex(String id) {
		List<T> tabs = getTabs();
		for (int index = 0 ; index < tabs.size() ; index ++) {
			if (tabs.get(index).getId().equals(id)) {
				return index;
			}
		}
		return -1;
	}

	private final ObjectProperty<Side> sideProperty = new SimpleObjectProperty();
	public final ReadOnlyObjectProperty<Side> sideProperty() {
		return sideProperty;
	}
	public void setSide(Side side) {
		sideProperty.set(side);
	}
	public Side getSide() {
		return sideProperty.get();
	}

	private final ObjectProperty<T> selectedTabProperty = new SimpleObjectProperty(null);
	public final ReadOnlyObjectProperty<T> selectedTabProperty() {
		return selectedTabProperty;
	}
	public void setSelectedTab(T tab) {
		selectedTabProperty.set(tab);
	}
	public T getSelectedTab() {
		return selectedTabProperty.get();
	}

	// Get View Area
	public ListView<T> getHeaderArea() {
		return headerArea;
	}
	public StackPane getContentArea() {
		return contentArea;
	}

	/* private Pane getInnerArea() {
		List<Node> children = getChildren();
		if (children.size() > 1) {
			return (Pane) children.get(0);
		}
		// (Inner)가 생성되지 않았다면 생성되기 이전임
		throw new NullPointerException();
	} */
}
