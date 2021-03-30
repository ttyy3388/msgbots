package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;

public class OpenDialogBoxAction implements Action {

    public static void execute(DialogWrap dialog) {
        dialog.dopen();
    }

    @Override
    public String getName() {
        return "open.dialog.box.action";
    }
}