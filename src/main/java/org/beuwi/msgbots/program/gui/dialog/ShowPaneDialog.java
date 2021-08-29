package org.beuwi.msgbots.program.gui.dialog;

import org.beuwi.msgbots.program.gui.dialog.base.DialogType;
import org.beuwi.msgbots.program.gui.dialog.base.DialogWrapper;

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

	@Override
	protected abstract boolean onInit();
	@Override
	protected abstract boolean onOpen();
	@Override
	protected abstract boolean onAction();
	@Override
	protected abstract boolean onClose();
}
