package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Side;

import org.beuwi.msgbots.view.gui.control.base.TabViewBase;
import org.beuwi.msgbots.view.gui.layout.StackPane;

public class TabView extends TabViewBase<TabItem> {
	// 원래는 헤더 높이를 강제했었으나, Border 같은 내부 길이가 길어지는 스타일 적용 시
	// 스크롤 바가 생기는 등 까다로운 조건이 발생해서, 그냥 헤더 넓이는 탭 헤더에 따라 알아서 적용되도록 내둠
	private final ListView header = getHeaderArea();
	private final StackPane content = getContentArea();

	public TabView() {
		this(null);
	}

	public TabView(TabItem... tabs) {
		super(tabs);

		header.setMinHeight(30); // 헤더 영역 높이 강제
		header.setMaxHeight(30); // 헤더 영역 높이 강제

		addChangeListener("fitHeader", change -> {
			header.setFitWidth(isFitHeader());
			header.setFitHeight(isFitHeader());
		});

		setSide(Side.TOP);
		// setPrefWidth(500);
		// setPrefHeight(600);
		addStyleClass("tab-view");
	}

	// 말 그대로 뷰를 토글할 수 있는 기능이고,
	// 두 번 같은 탭을 누르면 닫히고 다른 탭을 누르면 열림
	/* private final BooleanProperty togglableProperty = new SimpleBooleanProperty();
	public void setTogglable(boolean value) {
		togglableProperty.set(value);
	}
	public boolean isTogglable() {
		return togglableProperty.get();
	}
	public BooleanProperty togglableProperty() {
		return togglableProperty;
	} */

	// 해당 옵션을 활성화 하면 탭 아이템의 헤더 너비가 꽉 채워짐
	// EX : [[Header]      ] 에서 [[   Header   ]]
	private final BooleanProperty fitHeaderProperty = new SimpleBooleanProperty();
	public final BooleanProperty fitHeaderProperty() {
		return fitHeaderProperty;
	}
	public void setFitHeader(boolean value) {
		fitHeaderProperty.set(value);
	}
	public boolean isFitHeader() {
		return fitHeaderProperty.get();
	}
}
