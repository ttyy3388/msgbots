package org.beuwi.msgbots.program.app.view.actions;

import javafx.application.Platform;
import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.gui.dialog.base.DialogWrapper;

public class OpenDialogBoxAction implements Executor {
    private static OpenDialogBoxAction instance = null;

    // APP 외부의 클래스에서 접근할 여지가 있는 액션은 FX-Thread에서 실행해야 함
    public void execute(DialogWrapper dialog) {
        Platform.runLater(dialog::open);
    }

    public static OpenDialogBoxAction getInstance() {
        if (instance == null) {
            instance = new OpenDialogBoxAction();
        }
        return instance;
    }
}