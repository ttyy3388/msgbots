package org.beuwi.msgbots.platform.gui.dialog;

import org.beuwi.msgbots.platform.gui.type.DialogType;
import org.beuwi.msgbots.platform.gui.dialog.base.DialogWrapper;

public abstract class ShowPaneDialog extends DialogWrapper {
	// private final String title;
	// private final Node content;

	// Default : 400, 500
	public ShowPaneDialog() {
		super(DialogType.NONE);

		// this.title = title;
		// this.content = content;

		// root.setPrefWidth(400);
		// root.setPrefHeight(width);
		/* if (content != null) {
			setContent(content);
		} */
		setMargin(0);
		setUseFooterBar(false);
	}
}
