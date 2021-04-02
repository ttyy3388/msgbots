package org.beuwi.msgbots.platform.gui.control;

import javafx.collections.ListChangeListener;

import org.beuwi.msgbots.platform.app.action.CopyStringAction;
import org.beuwi.msgbots.platform.app.action.OpenDesktopAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;
import org.beuwi.msgbots.platform.gui.base.Control;
import org.beuwi.msgbots.platform.util.SharedValues;

public class BotView extends ListView<BotItem> implements Control {
	private static final String DEFAULT_STYLE_CLASS = "bot-view";

	private final ContextMenu menu;

	public BotView() {
		menu = new ContextMenu(
			new MenuItem("New Bot", "Ctrl + N", event -> {
				OpenDialogBoxAction.execute(new CreateBotDialog());
			}),
			new Separator(),
			new MenuItem("Show in Explorer", "Shift + Alt + R", event -> {
				OpenDesktopAction.execute(SharedValues.getFile("BOT_FOLDER_FILE"));
			}),
			new Separator(),
			new MenuItem("Copy Path", "Ctrl + Alt + C", event -> {
				CopyStringAction.execute(SharedValues.getString("BOT_FOLDER_PATH"));
			}),
			new MenuItem("Copy Relative Path", "Ctrl + Shift + C", event -> {
				CopyStringAction.execute(SharedValues.getString("BOT_FOLDER_PATH"));
			})
		);
		menu.setNode(this);

		getItems().addListener((ListChangeListener<BotItem>) change -> {
			while (change.next()) {
				for (BotItem item : change.getRemoved()) {
					item.setView(null);
				}
				for (BotItem item : change.getAddedSubList()) {
					item.setView(this);
				}
			}
		});

		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	/* public BotItem findById(String id) {
		return (BotItem) findById(this, id);
	} */

	public BotItem getItem(String name) {
		return (BotItem) findById(this, name);
	}
}