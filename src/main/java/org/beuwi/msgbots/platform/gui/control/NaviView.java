package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ObservableList;
import javafx.geometry.Side;
import org.beuwi.msgbots.platform.gui.control.base.TabItemBase;
import org.beuwi.msgbots.platform.gui.control.base.TabViewBase;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class NaviView extends TabViewBase {
	private static final String DEFAULT_STYLE_CLASS = "navi-view";

	private final static int DEFAULT_VIEW_WIDTH = 500;
	private final static int DEFAULT_VIEW_HEIGHT = 600;

	private final ListView headerArea = getHeaderArea();
	private final StackPane contentArea = getContentArea();

	private static final int DEFAULT_HEADER_WIDTH = 150;

	public NaviView() {
		this(null);
	}

	public NaviView(NaviItem... items) {
		super(items);

		headerArea.setMinWidth(DEFAULT_HEADER_WIDTH);
		headerArea.setMaxWidth(DEFAULT_HEADER_WIDTH);

		setSide(Side.LEFT);
		// setMargin(DEFAULT_VIEW_MARGIN);
		setPrefWidth(DEFAULT_VIEW_WIDTH);
		setPrefHeight(DEFAULT_VIEW_HEIGHT);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public ObservableList<TabItemBase> getNavis() {
		return getTabs();
	}
}
