package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.BotListTab;
import org.beuwi.msgbots.platform.gui.control.BotItem;
import org.beuwi.msgbots.platform.gui.control.BotView;

import java.io.File;

public class RefreshBotListAction implements Action {
	private static BotView botView;

	@Override
	public void init() {
		botView = BotListTab.getComponent();
	}

	public static void execute() {
		botView.getItems().clear();

		for (File folder : FileManager.getBotList()) {
			botView.getItems().add(new BotItem(folder.getName()));
		}
	}

	@Override
	public String getName() {
		return "refresh.bot.list.action";
	}
}
