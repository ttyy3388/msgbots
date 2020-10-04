package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.control.Document;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class OpenDocumentTabAction implements Action
{
	@Override
	public void init()
	{

	}

	public static void execute(String title, Document document)
	{
		AddMainAreaTabAction.execute(new Tab(title, document));
	}

	@Override
	public String getName()
	{
		return "open.document.tab.action";
	}
}
