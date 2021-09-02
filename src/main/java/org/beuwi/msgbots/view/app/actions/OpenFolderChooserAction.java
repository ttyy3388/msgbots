package org.beuwi.msgbots.view.app.actions;

import javafx.stage.DirectoryChooser;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.util.ViewManager;

import java.io.File;

public class OpenFolderChooserAction implements Executor {
    private static OpenFolderChooserAction instance = null;

    public File execute(String title) {
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle(title);

        return folderChooser.showDialog(ViewManager.getStage());
    }

    public static OpenFolderChooserAction getInstance() {
        if (instance == null) {
            instance = new OpenFolderChooserAction();
        }
        return instance;
    }
}
