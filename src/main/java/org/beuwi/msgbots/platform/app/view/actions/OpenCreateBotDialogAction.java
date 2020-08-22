package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.dialogs.CreateBotDialog;

public class OpenCreateBotDialogAction implements Action
{
    private static CreateBotDialog dialog;

    @Override
    public void init()
    {
        dialog = new CreateBotDialog();
    }

    public static void execute()
    {
        dialog.init();
        dialog.open();
    }

    @Override
    public String getName()
    {
        return "open.create.bot.dialog.action";
    }
}
