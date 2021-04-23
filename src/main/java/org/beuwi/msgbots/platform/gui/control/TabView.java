package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import org.beuwi.msgbots.platform.gui.control.base.TabViewBase;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class TabView extends TabViewBase<TabItem> {
	private static final String DEFAULT_STYLE_CLASS = "tab-view";

	private final static int DEFAULT_VIEW_WIDTH = 500;
	private final static int DEFAULT_VIEW_HEIGHT = 600;

	private final ListView headerArea = getHeaderArea();
	private final StackPane contentArea = getContentArea();

	// private static final int DEFAULT_HEADER_WIDTH = 100;
	private static final int DEFAULT_HEADER_HEIGHT = 30;

	public TabView() {
		this(null);
	}

	public TabView(TabItem... tabs) {
		super(tabs);

		fitHeaderProperty().addListener(change -> {
			headerArea.setFitWidth(isFitHeader());
		});

		headerArea.setMinHeight(DEFAULT_HEADER_HEIGHT);
		headerArea.setMaxHeight(DEFAULT_HEADER_HEIGHT);

		// setMargin(DEFAULT_VIEW_MARGIN);
		setPrefWidth(DEFAULT_VIEW_WIDTH);
		setPrefHeight(DEFAULT_VIEW_HEIGHT);
		// getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	// 해당 옵션을 활성화 하면 탭 아이템의 헤더 너비가 꽉 채워짐
	// EX : [[Header]                 ] 에서 [[         Header        ]]
	private final BooleanProperty fitHeaderProperty = new SimpleBooleanProperty();
	/* private final InvalidationListener fittableListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {

		}
	}; */
	public void setFitHeader(boolean value) {
		fitHeaderProperty.set(value);
	}
	public boolean isFitHeader() {
		return fitHeaderProperty.get();
	}
	public BooleanProperty fitHeaderProperty() {
		return fitHeaderProperty;
	}
}
