package org.beuwi.msgbots.platform.app.view.actions;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.app.impl.Action;

public class ToggleMenuBarAction implements Action
{
    private static BorderPane bpane;
    private static StackPane spane;

    private static boolean hided = false;

    @Override
    public void init()
    {
        // spane = MenuBarPart.getRoot();
        // bpane = MainView.getRoot();
    }

    public static void execute()
    {
        if (hided)
        {
            bpane.setTop(spane);
        }
        else
        {
            bpane.getChildren().remove(spane);
        }

        hided = !hided;
    }

    @Override
    public String getName()
    {
        return "hide.menu.bar.action";
    }
}
