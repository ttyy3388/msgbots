package org.beuwi.msgbots.program.gui.control;

import javafx.geometry.Side;

import org.beuwi.msgbots.program.gui.control.base.TabViewBase;
import org.beuwi.msgbots.program.gui.layout.StackPane;

public class NaviView extends TabViewBase<NaviItem> {
	private final ListView<NaviItem> headerArea = getHeaderArea();
	private final StackPane contentArea = getContentArea();

	public NaviView() {
		this(null);
	}

	public NaviView(NaviItem... items) {
		super(items);

		headerArea.setMinWidth(150);
		headerArea.setMaxWidth(150);

		setSide(Side.LEFT);
		// setMargin(150);
		setPrefWidth(500);
		setPrefHeight(600);
		getStyleClass().add("navi-view");
	}
}
