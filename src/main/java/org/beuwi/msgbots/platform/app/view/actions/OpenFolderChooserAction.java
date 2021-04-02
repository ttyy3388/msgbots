package org.beuwi.msgbots.platform.app.view.actions;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.beuwi.msgbots.platform.app.impl.Action;
import org.beuwi.msgbots.platform.app.view.MainView;

import java.io.File;

public class OpenFolderChooserAction implements Action {
    @Override
    public void init() {
    }

    public static File execute() {
        return execute("Choose Directory");
    }

    public static File execute(String title) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        return directoryChooser.showDialog(MainView.getStage());
    }

    @Override
    public String getName() {
        return "open.folder.chooser.action";
    }
}
