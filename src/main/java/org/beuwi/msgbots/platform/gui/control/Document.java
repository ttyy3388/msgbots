package org.beuwi.msgbots.platform.gui.control;

public class Document extends Tab {

	private final Page page;

	public Document(String name, Page page)
	{
		this.page = page;

		setText(name);
		setContent(page);
	}

	public Page getPage()
	{
		return page;
	}
}
