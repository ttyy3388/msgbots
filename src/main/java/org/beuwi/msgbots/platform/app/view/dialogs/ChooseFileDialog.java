package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.UpdateBotsPathAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.awt.Toolkit;
import java.io.File;
import java.util.List;

public class ChooseFileDialog extends DialogWrap {
    private final ObservableMap<String, Object> namespace;
    private final FormLoader loader;
    private final VBox root;

    @FXML private TextField txfFilePath;
    @FXML private Button btnChooseFile;

    private final Button btnUpdate;
    private final Button btnCancel;

    private File file;

    public ChooseFileDialog(String description, String... extensions) {
        this(new ExtensionFilter(description, extensions));
    }

    public ChooseFileDialog(ExtensionFilter filter) {
        loader = new FormLoader("dialog", "choose-file-dialog", this);
        namespace = loader.getNamespace();
        root = loader.getRoot();

        btnUpdate = getActionButton();
        btnCancel = getCancelButton();

        btnUpdate.setText("Update");

        btnChooseFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose File");

            file = fileChooser.showOpenDialog(MainView.getStage());

            if (file != null) {
                txfFilePath.setText(file.getAbsolutePath());
            }
        });

        txfFilePath.textProperty().addListener(change -> {
            btnUpdate.setDisable(txfFilePath.getText().isEmpty());
        });

        Platform.runLater(() -> {
            txfFilePath.requestFocus();
        });
    }

    @Override
    protected void open() {
        setContent(root);
        setTitle("Choose File");
    }

    @Override
    protected boolean action() {
        return true;
    }
}