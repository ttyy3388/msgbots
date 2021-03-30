package org.beuwi.msgbots.platform.app.view.actions;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.gui.control.Label;

import java.io.File;

public class UpdateCurrentPathAction implements Action {
    private static Label lblCurrentPath;

    @Override
    public void init() {
        lblCurrentPath = StatusBarPart.getComponent("lblCurrentPath");
    }

    public static void execute(File file) {
        execute(file.getAbsolutePath());
    }

    public static void execute(String data) {
        lblCurrentPath.setText(data);
    }

    @Override
    public String getName() {
        return "update.current.path.action";
    }
}
