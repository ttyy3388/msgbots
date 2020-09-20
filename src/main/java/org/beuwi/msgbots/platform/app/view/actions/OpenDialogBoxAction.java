package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.dialog.DialogBoxWrap;

public class OpenDialogBoxAction implements Action
{
    @Override
    public void init()
    {

    }

    public static void execute(DialogBoxWrap dialog)
    {
        dialog.open();
    }

    @Override
    public String getName()
    {
        return "open.dialog.box.action";
    }
}
