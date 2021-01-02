package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.Side;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import org.beuwi.msgbots.platform.gui.skins.TabViewSkin;

/* Tab View [ Header Area [ Headers [ Tab Header ( Main ) ] ] , Content Area [ Tab Content ] ] */
public class TabView extends Control {
	private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");
	// private static final PseudoClass PINNED_PSEUDO_CLASS = PseudoClass.getPseudoClass("pinned");
	// public static final PseudoClass NORMAL_PSEUDO_CLASS = PseudoClass.getPseudoClass("normal");
	// public static final PseudoClass SYSTEM_PSEUDO_CLASS = PseudoClass.getPseudoClass("system");

	private final DoubleProperty tabWidth = new SimpleDoubleProperty(100);
	private final DoubleProperty tabHeight = new SimpleDoubleProperty(30);

	private final ObjectProperty<TabItem> selectedTab = new SimpleObjectProperty(null);
	private final ObjectProperty<Side> type = new SimpleObjectProperty(Side.TOP);

	private final ObservableList<TabItem> list = FXCollections.observableArrayList();

	public TabView() {
		this(null);
	}

	public TabView(TabItem... tabs) {
		getTabs().addListener((ListChangeListener<TabItem>) change -> {
			while (change.next()) {
				for (TabItem tab : change.getRemoved()) {
					tab.setView(null);
				}
				for (TabItem tab : change.getAddedSubList()) {
					tab.setView(this);
					selectTab(tab);
				}
			}
		});

		if (tabs != null) {
			addTab(tabs);
		}

		selectedTabProperty().addListener((observable, oldTab, newTab) -> {
			if (oldTab != null) {
				oldTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}
			newTab.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	/* ---------------------------------------- */

	public void setType(Side side) {
		typeProperty().set(side);
	}

	public void setTabWidth(double value) {
		tabWidthProperty().set(value);
	}

	public void setTabHeight(double value) {
		tabHeightProperty().set(value);
	}

	public Side getType() {
		return typeProperty().get();
	}

	public double getTabWidth() {
		return tabWidthProperty().get();
	}

	public double getTabHeight() {
		return tabHeightProperty().get();
	}

	public ObjectProperty<Side> typeProperty() {
		return type;
	}

	public DoubleProperty tabWidthProperty() {
		return tabWidth;
	}

	public DoubleProperty tabHeightProperty() {
		return tabHeight;
	}

	/* ---------------------------------------- */

	public void addTab(TabItem... tabs) {
		getTabs().addAll(tabs);
	}

	public void removeTab(TabItem tabs) {
		getTabs().removeAll(tabs);
	}

	public void selectTab(TabItem tab) {
		selectedTabProperty().set(tab);
	}

	public ObservableList<TabItem> getTabs() {
		return list;
	}

	public TabItem getSelectedTab() {
		return selectedTabProperty().get();
	}

	public ObjectProperty<TabItem> selectedTabProperty() {
		return selectedTab;
	}

	@Override
	public Skin<?> createDefaultSkin() {
		return new TabViewSkin(this);
	}
}