package org.beuwi.msgbots.program.gui.control;

import javafx.collections.ListChangeListener;

public class BotView extends ListView<BotItem> {
	public BotView() {
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