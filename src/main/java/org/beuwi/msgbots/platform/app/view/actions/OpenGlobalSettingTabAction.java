package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalSettingTab;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class OpenGlobalSettingTabAction implements Action
{
    private static StackPane pane;

    @Override
    public void init()
    {
        pane = GlobalSettingTab.getRoot();
    }

    public static void execute()
    {
        AddEditorPaneTabAction.execute(new Tab("Settings", pane));
    }

    @Override
    public String getName()
    {
        return "open.global.settings.tab.action";
    }
}