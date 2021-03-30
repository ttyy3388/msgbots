package org.beuwi.msgbots.platform.gui.skins;

import javafx.scene.control.ScrollBar;
import javafx.scene.input.ScrollEvent;

public class ScrollBarSkin extends javafx.scene.control.skin.ScrollBarSkin {
	private static final double DEFAULT_SCROLL_SPEED = 0.005;

	public ScrollBarSkin(ScrollBar control) {
		super(control);

		control.setPrefWidth(10.0);
		control.addEventFilter(ScrollEvent.SCROLL, event -> {
			control.setValue(control.getValue() - (event.getDeltaY() * DEFAULT_SCROLL_SPEED));
		});

		/* if (control.getParent() != null){
			// ScrollPane, VirtualFlow 등등이 나옴
			Parent parent = control.getParent();
			// TextArea, ListView 등등이 나옴
			Parent target = parent.getParent();
			// 텍스트 박스 스크롤 속도 조정
			if (target instanceof TextArea){
				TextArea textarea = (TextArea) target;
				ScrollBar scrollbar = (ScrollBar) textarea.lookup(".scroll-bar:vertical");

			}
		} */
	}
}