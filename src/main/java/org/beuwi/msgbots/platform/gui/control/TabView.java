package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.platform.gui.control.base.TabItemBase;
import org.beuwi.msgbots.platform.gui.control.base.TabViewBase;
import org.beuwi.msgbots.platform.gui.layout.HBox;
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

		fittableProperty().addListener(change -> {
			headerArea.setFitWidth(isFittable());
		});

		headerArea.setMinHeight(DEFAULT_HEADER_HEIGHT);
		headerArea.setMaxHeight(DEFAULT_HEADER_HEIGHT);

		// setMargin(DEFAULT_VIEW_MARGIN);
		setPrefWidth(DEFAULT_VIEW_WIDTH);
		setPrefHeight(DEFAULT_VIEW_HEIGHT);
		// getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	private final BooleanProperty fittableProperty = new SimpleBooleanProperty();
	/* private final InvalidationListener fittableListener = new InvalidationListener() {
		@Override
		public void invalidated(Observable observable) {

		}
	}; */
	public void setFittable(boolean fittable) {
		fittableProperty.set(fittable);
	}
	public boolean isFittable() {
		return fittableProperty.get();
	}
	public BooleanProperty fittableProperty() {
		return fittableProperty;
	}
}
