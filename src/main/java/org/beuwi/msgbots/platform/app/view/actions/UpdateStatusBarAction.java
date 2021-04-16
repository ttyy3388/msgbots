package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.control.Label;

public class UpdateStatusBarAction implements Action {
    private static Label lblCurrentPath;
    private static Label lblInfoMessage;

    @Override
    public void init() {
        lblCurrentPath = (Label) StatusBarPart.getComponent("lblCurrentPath");
        lblInfoMessage = (Label) StatusBarPart.getComponent("lblInfoMessage");
    }

    // 공백("")일 경우 반영시키나 널(null)일 경우는 막음
    public static void execute(String[] data) {
        if (data[0] != null) {
            lblCurrentPath.setText(data[0]);
        }
        if (data[1] != null) {
            lblInfoMessage.setText(data[1]);
        }
    }

    @Override
    public String getName() {
        return "update.status.bar.action";
    }
}
