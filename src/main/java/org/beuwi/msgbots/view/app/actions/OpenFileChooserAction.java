package org.beuwi.msgbots.view.app.actions;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.beuwi.msgbots.base.impl.Executor;
import org.beuwi.msgbots.view.util.ViewManager;

import java.io.File;

public class OpenFileChooserAction implements Executor {
    private static OpenFileChooserAction instance = null;

    public File execute(String title, String description, String... extensions) {
        return execute(title, new FileChooser.ExtensionFilter(description, extensions));
    }

    public File execute(String title, ExtensionFilter filter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setTitle(title);

        return fileChooser.showOpenDialog(ViewManager.getStage());
    }

    public static OpenFileChooserAction getInstance() {
        if (instance == null) {
            instance = new OpenFileChooserAction();
        }
        return instance;
    }
}
