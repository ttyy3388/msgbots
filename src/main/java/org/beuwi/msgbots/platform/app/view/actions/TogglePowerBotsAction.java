package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.app.view.navis.BotListNavi;
import org.beuwi.msgbots.platform.gui.control.Bot;
import org.beuwi.msgbots.platform.gui.control.BotView;

public class TogglePowerBotsAction implements View {
	private static BotView control;

	private static boolean power = false;

	@Override
	public void init() {
		control = BotListNavi.getComponent();
	}

	public static void execute() {
		for (Bot bot : control.getItems()) {
            bot.setPower(power);
		}
		power = !power;
	}
}
