package org.beuwi.simulator.platform.ui.dialog;

public enum IDialogBoxType
{
	// Settings, Create, Import ... (다수의 컴포넌트)
	// final public static int APPLICATION = 0;

	// Exists, Rename, Error ... (1 ~ 2개의 간단한 컴포넌트)
	// final public static int DOCUMENT = 1;

	APPLICATION, DOCUMENT;

    public enum DOCUMENT
	{
		ERROR, WARNING, EVENT
	}

	// NONE, ERROR, WARNING, EVENT
}