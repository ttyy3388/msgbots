package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.view.gui.control.base.ScrollViewBase;

public class ScrollView extends ScrollViewBase {
	private static final double SCROLL_SPEED = 0.005;

	public ScrollView() {
		this(null);
	}

	public ScrollView(Node node) {
		if (node != null) {
			setContent(node);
		}

		addChangeListener("content", change1 -> {
			Node content = getContent();
			if (content != null) {
				if (content instanceof Pane pane) {
					// 자동 스크롤이 켜져 있다면 제일 아래로 스크롤 되도록
					addChangeListener(getFXProperty(pane, "height"), change2 -> {
						if (isAutoScroll()) {
							setVvalue(1.0d);
						}
					});
				}
			}
		});

		addEventFilter(ScrollEvent.SCROLL, event -> {
			setHvalue(getHvalue() - (event.getDeltaY() * SCROLL_SPEED));
			setVvalue(getVvalue() - (event.getDeltaY() * SCROLL_SPEED));
		});

		setFitToWidth(true);
		setFitToHeight(true);
		addStyleClass("scroll-pane");
	}

	// 해당 프로펄티가 활성화일 때는 자동으로 마지막 아이템으로 스크롤 함.
	private final BooleanProperty autoScrollProperty = new SimpleBooleanProperty();
	public final BooleanProperty autoScrollProperty() {
		return autoScrollProperty;
	}
	public void setAutoScroll(boolean value) {
		autoScrollProperty.set(value);
	}
	public boolean isAutoScroll() {
		return autoScrollProperty.get();
	}
}