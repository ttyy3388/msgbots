package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.beuwi.simulator.platform.application.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IContextMenuType;

public class ShowExplorerOptionAction
{
    private static IContextMenu option;
    private static Button button;

    public static void initialize()
    {
        option = new IContextMenu(IContextMenuType.OPTION_EXPLORER);
        button = (Button) ActiveAreaPart.getNameSpace().get("btnShowOption");
    }

    public static void update(MouseEvent event)
    {
        if (!event.isPrimaryButtonDown())
        {
            return ;
        }

        option.show(button, event.getScreenX(), event.getScreenY());
    }
}