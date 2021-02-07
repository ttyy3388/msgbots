package org.beuwi.msgbots.platform.gui.control;

import org.beuwi.msgbots.platform.gui.layout.VBox;

public class ToastView extends VBox<ToastItem> {
	private static final String DEFAULT_STYLE_CLASS = "toast-view";

	private static final int DEFAULT_PREF_WIDTH = 500;
	private static final int DEFAULT_GAP_VALUE = 10;

	public ToastView() {
		/* getItems().addListener((ListChangeListener<ToastItem>) change -> {
			for (ToastItem toast : getItems()) {
				toast.setView(this);
			}
		}); */

		setSpacing(DEFAULT_GAP_VALUE);
		setPrefWidth(DEFAULT_PREF_WIDTH);
	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
}

/* public class ToastView extends ListView<ToastItem> {
	private static final String DEFAULT_STYLE_CLASS = "toast-view";

	private static final int DEFAULT_PREF_WIDTH = 500;
	// private static final int DEFAULT_GAP_VALUE = 10;

	public ToastView() {
		getItems().addListener((ListChangeListener<ToastItem>) change -> {
			for (ToastItem toast : getItems()) {
				toast.setView(this);
			}
		});

		// setSpacing(DEFAULT_GAP_VALUE);
		setPrefWidth(DEFAULT_PREF_WIDTH);
	    getStyleClass().add(DEFAULT_STYLE_CLASS);
	}
} */