package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.ToastListPart;
import org.beuwi.msgbots.platform.gui.control.ToastItem;
import org.beuwi.msgbots.platform.gui.control.ToastView;

public class ShowToastMessageAction implements Action {
    private static ToastView control;

    @Override
    public void init() {
        control = ToastListPart.getComponent("tsvToastList");
    }

    public static void execute(ToastItem item) {
        control.getItems().add(item);
    }

    @Override
    public String getName() {
        return "show.toast.message.action";
    }
}
