package org.beuwi.msgbots.platform.gui.editor;

import org.beuwi.msgbots.platform.gui.control.TextArea;

public class CodeArea extends TextArea
{
	public CodeArea()
    {
        this(null);
    }

	public CodeArea(String text)
	{
		if (text != null)
		{
			setText(text);
		}
	}
}
