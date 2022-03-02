package org.beuwi.msgbots.view.gui.control;

import javafx.geometry.Side;

import org.beuwi.msgbots.view.gui.control.base.TabViewBase;
import org.beuwi.msgbots.view.gui.layout.StackPane;

public class NaviView extends TabViewBase<NaviItem> {
	private final ListView<NaviItem> header = getHeaderArea();
	private final StackPane content = getContentArea();

	public NaviView() {
		this(null);
	}

	public NaviView(NaviItem... items) {
		super(items);

		header.setMinWidth(150);
		header.setPrefWidth(150);
		header.setMaxWidth(150);

		setSide(Side.LEFT);
		// setMargin(150);
		setPrefWidth(500);
		setPrefHeight(600);
		addStyleClass("navi-view");
	}
}
