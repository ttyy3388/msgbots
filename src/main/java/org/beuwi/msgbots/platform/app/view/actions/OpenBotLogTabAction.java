package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;

public class OpenBotLogTabAction implements Action {
    private static TabView control;

    @Override
    public void init() {
        control = MainAreaPart.getComponent();
    }

    public static void execute(String name) {
        String id = "LOG:" + name;
        if (control.getTab(id) != null) {
            control.selectTab(control.getTab(id));
        }
        else {
            LogView logView = new LogView(name);

            FileManager.link(FileManager.getBotLog(name), () -> {
                logView.getItems().setAll(LogManager.load(name));
            });

            AddMainAreaTabAction.execute(new TabItem("LOG:" + name, logView));
        }
    }

    @Override
    public String getName() {
        return "open.bot.log.tab.action";
    }
}
