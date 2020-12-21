package org.beuwi.msgbots.platform.gui.control;

import com.sun.javafx.scene.control.behavior.TabPaneBehavior;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.enums.ControlType;
import org.beuwi.msgbots.platform.gui.skins.TabViewSkin;

import java.util.ArrayList;
import java.util.List;

// @DefaultProperty("tabs")
public class TabView extends Control {
	private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");

	private final ObjectProperty<ControlType> type = new SimpleObjectProperty(null);
	// Selected Tab Property
	private final ObjectProperty<Tab> selected = new SimpleObjectProperty(null);

	private final ObservableList<Tab> tabs = FXCollections.observableArrayList();

	public TabView() {
		this(null);
	}

	public TabView(Tab... tabs) {
		if (tabs != null) {
			addTabs(tabs);
		}

		getTabs().addListener((ListChangeListener<Tab>) change -> {
			while (change.next()) {
				for (Tab tab : change.getRemoved()) {
					tab.setView(null);
				}

				for (Tab tab : change.getAddedSubList()) {
					tab.setView(this);

					if (getType() != null && getType().equals(ControlType.SYSTEM)) {
						HBox.setHgrow(tab, Priority.ALWAYS);
					}

					selected.setValue(tab);
				}

				this.setVisible(!getTabs().isEmpty());
			}
		});

		getSelectedTabProperty().addListener((observable, oldTab, newTab) -> {
			if (oldTab != null) {
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}

			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					case W : closeTab(); break;
					case TAB :
						if (!event.isShiftDown()) {
							selectNextTab();
						}
						break;
				}

				if (event.isShiftDown()) {
					switch (event.getCode()) {
						case TAB : selectPrevTab(); break;
					}
				}
			}
		});

		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public void selectTab(Tab tab) {
		selected.set(tab);
	}

	public void selectTab(int index) {
		selected.set(getTab(index));
	}

	public void selectTab(String name) {
		selected.set(getTab(name));
	}

	/* private void selectFirstTab() {
		selectTab(0);
	}

	private void selectLastTab() {
		selectTab(tabs.size() - 1);
	} */

	private void selectNextTab() {
		selectNextTab(getSelectedTab());
	}

	public void selectNextTab(Tab tab) {
		int index = getTabIndex(tab), size = tabs.size();

		if (size < 1 || index < 0) {
			return ;
		}

		// If have a next tab
		if (size > index + 1) {
			selectTab(index + 1);
		}
		// Select first tab
		else {
			selectTab(0);
		}
	}

	private void selectPrevTab() {
		selectPrevTab(getSelectedTab());
	}

	public void selectPrevTab(Tab tab) {
		int index = getTabIndex(tab), size = tabs.size();

		if (size < 1 || index < 0) {
			return ;
		}

		// If have a previous tab
		try {
			selectTab(index - 1);
		}
		// 만약 0번째 탭에서 이전탭으로 이동하려고 한다면 에러가 발생하기에 편법사용
		catch (IndexOutOfBoundsException e) {
			selectTab(size - 1);
		}
	}

	public void addTab(Tab tab) {
		if (containsTab(tab)) {
			selectTab(getTabIndex(tab));
		}
		else {
			getTabs().add(tab);
		}
	}

	public void addTabs(Tab... tabs) {
		for (Tab tab : tabs) {
			addTab(tab);
		}
	}

	public boolean containsTab(Tab tab) {
		return getTabIndex(tab) != -1;
	}

	public boolean containsTab(String title) {
		return getTabIndex(title) != -1;
	}

	public int getTabIndex(Tab tab) {
		return getTabIndex(tab.getText());
	}

	// 추후 ID 방식으로 바꿔야함
	public int getTabIndex(String title) {
		for (int index = 0 ; index < tabs.size() ; index ++) {
			if (tabs.get(index).getText().equals(title)) {
				return index;
			}
		}

		return -1;
	}

	public void closeOtherTabs(Tab tab) {
		List<Tab> others = new ArrayList<>();

		for (Tab item : tabs) {
			if (tab.equals(item)) {
				continue;
			}

			others.add(item);
		}

		closeTab(others);
	}

	public void closeAllTabs() {
		closeTab(getTabs());
	}

	private void closeTab() {
		closeTab(getSelectedTab());
	}

	private void closeTab(List<Tab> list) {
		for (Tab item : list) {
			closeTab(item);
		}
	}

	public void closeTab(Tab tab) {
		if (!tab.isClosable()) {
			return ;
		}

		int index = getTabIndex(tab), size = tabs.size();

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

		tabs.remove(tab);
	}

	public void setType(ControlType type) {
		this.type.set(type);
	}

	public void setSelectedTab(Tab tab) {
		selected.set(tab);
	}

	public Tab getTab(String title) {
		return containsTab(title) ? getTab(getTabIndex(title)) : null;
	}

	public Tab getTab(int index) {
		return tabs.get(index);
	}

    public ControlType getType() {
        return type.get();
    }

	public Tab getSelectedTab() {
		return selected.get();
	}

	public ObservableList<Tab> getTabs() {
		return tabs;
	}

	public ObjectProperty<ControlType> getTypeProperty() {
        return type;
    }

	// Selected Tab Property
	public ObjectProperty<Tab> getSelectedTabProperty() {
		return selected;
	}

	public BooleanProperty getVisibleProperty() {
		return visibleProperty();
	}

	@Override
	public TabViewSkin createDefaultSkin() {
		return new TabViewSkin(this);
	}

	/* public void setTabWidth(double value) {
		size.set(value);
	}

	public void setTabHeight(double value) {
		height.set(value);
	} */
}