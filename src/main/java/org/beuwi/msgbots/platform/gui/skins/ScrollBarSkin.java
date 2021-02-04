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

		/* Parent parent = control.getParent();

		if (parent != null){
			if (parent.getParent() instanceof TextArea){
				TextArea textarea = (TextArea) parent.getParent();

				textarea.getScrollTopProperty().addListener(event ->{
					textarea.setScrollTop(textarea.getScrollTop() + 10);
				});
			}
		} */
	}
}