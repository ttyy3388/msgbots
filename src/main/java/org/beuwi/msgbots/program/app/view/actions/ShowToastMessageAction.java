package org.beuwi.msgbots.program.app.view.actions;

import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.app.view.parts.ToastListPart;
import org.beuwi.msgbots.program.gui.control.ToastItem;
import org.beuwi.msgbots.program.gui.control.ToastView;

public class ShowToastMessageAction implements Executor {
    private static ShowToastMessageAction instance = null;

    private final ToastView control = ToastListPart.getInstance().getToastView();

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
