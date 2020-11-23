package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.beuwi.msgbots.platform.app.action.OpenDesktopAction;
import org.beuwi.msgbots.platform.app.action.base.CopyAction;
import org.beuwi.msgbots.platform.util.SharedValues;

public class BotView extends ListView<Bot>
{
	private static final String DEFAULT_STYLE_CLASS = "bot-view";

	private final ContextMenu menu = new ContextMenu
	(
		new Menu("New Bot", "Ctrl + N" /*, event -> OpenDialogBoxAction.execute(new CreateBotDialog()) */),
		new Separator(),
		new Menu("Show in Explorer", "Shift + Alt + R", event -> OpenDesktopAction.execute(SharedValues.BOTS_FOLDER_FILE)),
		new Separator(),
		new Menu("Copy Path", "Ctrl + Alt + C", event -> CopyAction.execute(SharedValues.BOTS_FOLDER_PATH)),
		new Menu("Copy Relative Path", "Ctrl + Shift + C", event -> CopyAction.execute(SharedValues.BOTS_FOLDER_PATH))
	);

	public BotView()
	{
		menu.setNode(this);

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
}