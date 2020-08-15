package org.beuwi.msgbots.platform.gui.dialog;

import org.beuwi.msgbots.platform.gui.dialog.DialogBoxType.DOCUMENT;

// Dialog Box Wrapper
public abstract class DialogBoxWrap extends DialogBoxFrame
{
	public DialogBoxWrap(DOCUMENT document)
	{
		super(document);

		this.init();
	}

	public DialogBoxWrap(DialogBoxType type)
	{
		super(type);

		this.init();
	}

	public abstract void display();
	public abstract void init();
}