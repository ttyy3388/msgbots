package org.beuwi.msgbots.view.app.actions;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.app.parts.ToastViewPart;
import org.beuwi.msgbots.view.gui.control.ToastItem;
import org.beuwi.msgbots.view.gui.control.ToastView;

public class ShowToastMessageAction implements Executor {
    private static ShowToastMessageAction instance = null;

    private final ToastView control = ToastViewPart.getInstance().getToastView();

    public void execute(ToastItem item) {
        control.getItems().add(item);
    }

    public static ShowToastMessageAction getInstance() {
        if (instance == null) {
            instance = new ShowToastMessageAction();
        }
        return instance;
    }
}
