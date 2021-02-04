package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;

public class NoticeView extends ListView<NoticeItem> {
	private static final String DEFAULT_STYLE_CLASS = "notice-view";

	// private static final int DEFAULT_PREF_WIDTH = 500;
	// private static final int DEFAULT_GAP_VALUE = 10;

	public NoticeView() {
		/* getItems().addListener((ListChangeListener<NoticeItem>) change -> {
			for (NoticeItem toast : getItems()) {
				toast.setView(this);
			}
		}); */

		// setSpacing(DEFAULT_GAP_VALUE);
		// setPrefWidth(DEFAULT_PREF_WIDTH);
	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}