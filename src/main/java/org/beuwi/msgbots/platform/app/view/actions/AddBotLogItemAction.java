package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.LogItem;
import org.beuwi.msgbots.platform.gui.control.LogView;

public class AddBotLogItemAction implements Action {
	/* private static LogView control;

	@Override
	public void init() {
		control = GlobalLogTab.getComponent();
	}

	public static void execute(LogItem item) {
		control.getItems().add(item);
	}

	/* public static void execute(String name, LogItem item) {
		LogView logView = LogManager.getView(name);

		if (logView != null) {
			logView.addItem(log);
		}

		control.getItems().add(item);
	} */

	@Override
	public String getName() {
		return "add.bot.log.item.action";
	}
}
