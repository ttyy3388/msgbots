package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class BotView extends ListView<Bot>
{
	private static final String DEFAULT_STYLE_CLASS = "bot-view";

	private final ContextMenu menu = new ContextMenu
	(
		new Menu("Create New Bot"),
		new Menu("Open Bot Settings"),
		new Separator(),
		new Menu("Show in Explorer", "Shift + Alt + R"),
		new Separator(),
		new Menu("Copy Path", "Ctrl + Alt + C"),
		new Menu("Copy Relative Path", "Ctrl + Shift + C")
	);

	public BotView()
	{
		setContextMenu(menu);

		getItems().addListener((ListChangeListener<Bot>) change ->
		{
			while (change.next())
			{
				for (Bot bot : change.getRemoved())
				{
					bot.setView(null);
				}

				for (Bot bot : change.getAddedSubList())
				{
					bot.setView(this);
				}
			}
		});

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public Bot getBot(int index)
	{
		return getItem(index);
	}

	public Bot getBot(String id)
    {
        return getItem(id);
    }

    public ObservableList<Bot> getBots()
    {
		return getItems();
    }
}