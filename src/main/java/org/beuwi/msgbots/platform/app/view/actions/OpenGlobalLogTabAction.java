package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.tabs.GlobalLogTab;
import org.beuwi.msgbots.platform.gui.control.Tab;

public class OpenGlobalLogTabAction implements Action
{
    private static StackPane pane;

    @Override
    public void init()
    {
        pane = GlobalLogTab.getRoot();
    }

    public static void execute()
    {
        AddEditorPaneTabAction.execute(new Tab("GLOBAL LOG", pane));
    }

    @Override
    public String getName()
    {
        return "open.global.log.tab.action";
    }
}