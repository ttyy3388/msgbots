package org.beuwi.msgbots.view.app.actions;

import javafx.scene.layout.Pane;
import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.app.parts.MainAreaPart;
import org.beuwi.msgbots.view.gui.control.TabItem;
import org.beuwi.msgbots.view.gui.control.TabView;

public class OpenWebViewTabAction implements Executor {
    private static OpenWebViewTabAction instance = null;

    private final TabView tabView = MainAreaPart.getInstance().getTabView();

    public void execute(String title, Pane content) {
        execute(tabView.containsTab(title) ?
                tabView.getTab(title) :
                new TabItem(title, content));
    }

    public void execute(TabItem tabItem) {
        tabView.addTab(tabItem);
    }

    public static OpenWebViewTabAction getInstance() {
        if (instance == null) {
            instance = new OpenWebViewTabAction();
        }
        return instance;
    }
}