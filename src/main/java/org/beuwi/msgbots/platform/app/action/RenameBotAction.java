package org.beuwi.msgbots.platform.app.action;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.actions.DisplayErrorDialogAction;

import java.io.File;
import java.io.IOException;

public class RenameBotAction implements Action {
    public static void execute(String before, String after) {
        execute(FileManager.getBotFolder(before), FileManager.getBotFolder(after));
    }

    public static void execute(File before, File after) {
        if (after.exists()) {
            DisplayErrorDialogAction.execute(new IOException("Bot " + after.getName() + " already exists"));
        }
        else {
            before.renameTo(after);
        }
    }

    @Override
    public String getName() {
        return "rename.bot.action";
    }
}
