package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.Node;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.gui.control.TabView;
import org.beuwi.msgbots.platform.gui.editor.Editor;

public class SaveBotScriptTabAction implements Action {
    private static TabView control;

    @Override
    public void init() {
        control = MainAreaPart.getComponent();
    }

    public static void execute(String name) {
        execute(control.getTab(name));
    }

    public static void execute(TabItem tab) {
        if (tab != null) {
            Node content = tab.getContent();

            if (content instanceof Editor) {
                FileManager.save(FileManager.getBotScript(tab.getText()), ((Editor) content).getText());
            }
        }
        else {
            new Exception("Tab Item Must not be null");
        }
    }

    @Override
    public String getName() {
        return "save.bot.script.tab.action";
    }
}
