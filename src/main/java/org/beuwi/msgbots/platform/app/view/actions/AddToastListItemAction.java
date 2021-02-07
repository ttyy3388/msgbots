package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.ToastListPart;
import org.beuwi.msgbots.platform.gui.control.ToastItem;
import org.beuwi.msgbots.platform.gui.control.ToastView;

public class AddToastListItemAction implements Action {
    private static ToastView component;

    @Override
    public void init() {
        component = ToastListPart.getComponent();
    }

    public static void execute(ToastItem item) {
        component.getChildren().add(item);
    }

    @Override
    public String getName() {
        return "add.toast.list.item.action";
    }
}
