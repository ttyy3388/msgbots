package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.SeparatorMenuItem;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.OpenDesktopAction;

public class BotView extends ListView<Bot>
{
	public static final String DEFAULT_STYLE_CLASS = "bot-view";

	private final ContextMenu menu = new ContextMenu
	(
		new MenuItem("Create New Bot"),
		new MenuItem("Open Bot Settings"),
		new SeparatorMenuItem(),
		new MenuItem("Show in Explorer", "Shift + Alt + R", event -> OpenDesktopAction.execute(FileManager.BOTS_FOLDER)),
		new SeparatorMenuItem(),
		new MenuItem("Copy Path", "Ctrl + Alt + C"),
		new MenuItem("Copy Relative Path", "Ctrl + Shift + C")
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