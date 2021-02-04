package org.beuwi.msgbots.platform.gui.dialog;

import javafx.stage.Stage;

// Dialog Box Wrapper
public abstract class DialogWrap extends DialogFrame {
	public DialogWrap() {
		super(DialogType.NONE);
	}

	public DialogWrap(DialogType type) {
		super(type, new Stage());
	}

	@Override
	public abstract void open();
	// public abstract void init();

	@Override
	public abstract void action();
}