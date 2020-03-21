package org.beuwi.simulator.platform.application.actions;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.beuwi.simulator.platform.application.parts.ActiveAreaPart;
import org.beuwi.simulator.platform.ui.components.IContextMenu;
import org.beuwi.simulator.platform.ui.components.IContextMenuType;

public class ShowViewMenuAction
{
    private static IContextMenu menu;
    private static Button button;

    public static void initialize()
    {
        menu = new IContextMenu(IContextMenuType.MENU_FILE);
        button = (Button) ActiveAreaPart.getNameSpace().get("btnViewMenu");
    }

    public static void update(MouseEvent event)
    {
        if (!event.isPrimaryButtonDown())
        {
            return ;
        }

        menu.show(button, event.getScreenX(), event.getScreenY());
    }
}