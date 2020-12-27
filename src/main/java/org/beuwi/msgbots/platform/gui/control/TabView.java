package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.enums.ControlType;
import org.beuwi.msgbots.platform.gui.layout.ScrollPanel;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

import java.util.ArrayList;
import java.util.List;

// @DefaultProperty("tabs")
public class TabView extends VBox {
	private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private static final String HEADER_STYLE_CLASS = "tab-header-area";
	private static final String CONTENT_STYLE_CLASS = "tab-content-area";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");
	public static final PseudoClass NORMAL_PSEUDO_CLASS = PseudoClass.getPseudoClass("normal");
	public static final PseudoClass SYSTEM_PSEUDO_CLASS = PseudoClass.getPseudoClass("system");

	// private static final int DEFAULT_HEADER_WIDTH = 100;
	private static final int DEFAULT_HEADER_HEIGHT = 30;

	private final ObjectProperty<ControlType> type = new SimpleObjectProperty(null);
	private final ObjectProperty<Tab> selectedTab = new SimpleObjectProperty(null);

	private final ObservableList<Tab> tabs = FXCollections.observableArrayList();

	/* Tab View [ Header Area [ Headers [ Tab Header ( Main ) ] ] , Content Area [ Tab Content ] ] */

	// Tab Content Area
	private final StackPanel content = new StackPanel();

	// Tab Header Area [ Tab Header List ]
	private final ScrollPanel header = new ScrollPanel();

	// Tab Header List
	private final HBox<Tab> headers = new HBox();

	{
		VBox.setVgrow(content, Priority.ALWAYS);
	}

	public enum Type {
		NORMAL, SYSTEM
	}

	public TabView() {
		this(null);
	}

	public TabView(Tab... tabs) {
		if (tabs != null) {
			addTabs(tabs);
		}

		header.setHvalue(1.0d);
		header.setContent(headers);
		header.setFitToWidth(false);
		header.setFitToHeight(true);
		header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setMaxHeight(DEFAULT_HEADER_HEIGHT);
		header.setHbarPolicy(ScrollBarPolicy.NEVER);
		header.setVbarPolicy(ScrollBarPolicy.NEVER);
		// header.setMinHeight(DEFAULT_HEADER_HEIGHT);
		header.setPrefHeight(DEFAULT_HEADER_HEIGHT);
		// header.setMaxHeight(DEFAULT_HEADER_HEIGHT);
		header.getStyleClass().add(HEADER_STYLE_CLASS);

		content.getStyleClass().add(CONTENT_STYLE_CLASS);

		getTabs().addListener((ListChangeListener<Tab>) change -> {
			while (change.next()) {
				for (Tab tab : change.getRemoved()) {
					headers.getItems().remove(tab);
					tab.setView(null);
				}
				for (Tab tab : change.getAddedSubList()) {
					headers.getItems().add(tab);
					tab.setView(this);
					if (getType() != null && getType().equals(ControlType.SYSTEM)) {
						HBox.setHgrow(tab, Priority.ALWAYS);
					}
					setSelectedTab(tab);
				}
				this.setVisible(!getTabs().isEmpty());
			}
		});

		typeProperty().addListener(change -> {
			switch (getType()) {
				case NORMAL :
					header.setFitToWidth(false);
					for (Tab tab : getTabs()) {
						HBox.setHgrow(tab, Priority.NEVER);
					}
					break;

				case SYSTEM :
					header.setFitToWidth(true);
					for (Tab tab : getTabs()) {
						HBox.setHgrow(tab, Priority.ALWAYS);
					}
					break;
			}
		});
		// Content Area Change
		selectedTabProperty().addListener(change -> {
			Node target = getSelectedTab().getContent();
			content.getItems().setAll(target);
			// 변경된 탭으로 포커스 이동하도록
			target.requestFocus();
		});

		selectedTabProperty().addListener((observable, oldTab, newTab) -> {
			if (oldTab != null) {
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.isControlDown()) {
				switch (event.getCode()) {
					case W : closeTab(getSelectedTab()); break;
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

		setVisible(false);
		setFittable(true);
		getItems().setAll(header, content);
		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

    public void selectTab(Tab tab) {
        selectedTabProperty().set(tab);
    }

    public void selectTab(int index) {
        selectedTabProperty().set(getTab(index));
    }

    public void selectTab(String name) {
        selectedTabProperty().set(getTab(name));
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
		selectedTabProperty().set(tab);
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
		return selectedTabProperty().get();
	}

	public ObservableList<Tab> getTabs() {
		return tabs;
	}

	public ObjectProperty<ControlType> typeProperty() {
		return type;
	}

	// Selected Tab Property
	public ObjectProperty<Tab> selectedTabProperty() {
		return selectedTab;
	}

	/* public void setTabWidth(double value) {
		size.set(value);
	}

	public void setTabHeight(double value) {
		height.set(value);
	} */
}