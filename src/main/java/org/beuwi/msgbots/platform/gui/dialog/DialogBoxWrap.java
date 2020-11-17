package org.beuwi.msgbots.platform.gui.dialog;

// Dialog Box Wrapper
public abstract class DialogBoxWrap extends DialogBoxFrame
{
	public DialogBoxWrap()
	{

	}

	@Override
	public abstract void open();
	// public abstract void init();

	@Override
	public abstract void action();
}