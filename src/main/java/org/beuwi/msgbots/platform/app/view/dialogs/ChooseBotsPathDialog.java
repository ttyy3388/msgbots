package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

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

public class ChooseBotsPathDialog extends DialogWrap {
    private final ObservableMap<String, Object> namespace;
    private final FormLoader loader;
    private final VBox root;

    @FXML private TextField txfFolderPath;
    @FXML private Button btnChooseDir;

    private final Button btnUpdate;
    private final Button btnCancel;

    private File file;

    public ChooseBotsPathDialog() {
        loader = new FormLoader("dialog", "choose-botspath-dialog", this);
        namespace = loader.getNamespace();
        root = loader.getRoot();

        btnUpdate = getActionButton();
        btnCancel = getCancelButton();

        btnUpdate.setText("Update");

        btnChooseDir.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Directory");

            file = directoryChooser.showDialog(MainView.getStage());

            if (file != null) {
                txfFolderPath.setText(file.getAbsolutePath());
            }
        });

        String currentPath = SharedValues.getString("BOTS_FOLDER_PATH");
        // 현재 지정된 경로가 있다면 그 경로를 보여줌 (기본값 : 프로그램폴더)
        if (currentPath != null) {
            txfFolderPath.setText(currentPath);
        }
        txfFolderPath.textProperty().addListener(change -> {
            btnUpdate.setDisable(txfFolderPath.getText().isEmpty());
        });

        Platform.runLater(() -> {
            txfFolderPath.requestFocus();
        });
    }

    @Override
    protected void open() {
        setContent(root);
        setTitle("Choose Bots Path");
    }

    @Override
    protected void action() {
        if (txfFolderPath.getText().isEmpty()) {
            return;
        }
        String path = txfFolderPath.getText();
        if (!(new File(path).isDirectory())) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        GlobalSettings.setData("program:bots_path", path);
        UpdateBotsPathAction.execute(path);
    }
}