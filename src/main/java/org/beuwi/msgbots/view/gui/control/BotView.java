package org.beuwi.msgbots.view.gui.control;

import javafx.collections.ListChangeListener;
import org.beuwi.msgbots.actions.CopyClipboardAction;
import org.beuwi.msgbots.actions.OpenDesktopAction;
import org.beuwi.msgbots.shared.SharedValues;
import org.beuwi.msgbots.view.util.StdActions;

public class BotView extends ListView<BotItem> {
	public BotView() {
		new ContextMenu(
			StdActions.CREATE_BOT.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.SHOW_IN_EXPLORER.handler(event ->  {
				OpenDesktopAction.getInstance().execute(SharedValues.getFile("file.botFolder"));
			}).toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.COPY_PATH.handler(event -> {
				CopyClipboardAction.getInstance().execute(SharedValues.getString("path.botFolder"));
			}).toMenuItem(),
			StdActions.COPY_RELATIVE_PATH.handler(event -> {
				CopyClipboardAction.getInstance().execute(SharedValues.getString("path.botFolder"));
			}).toMenuItem()
		).setNode(this);

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

		getStyleClass().add("bot-view");
	}

	@Override
	public BotItem findById(String name) {
		for (BotItem item : getItems()) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}
}