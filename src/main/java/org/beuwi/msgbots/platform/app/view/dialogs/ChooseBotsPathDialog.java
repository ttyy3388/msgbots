package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.action.CreateBotAction;
import org.beuwi.msgbots.platform.app.event.ChooseDirectoryEvent;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.RefreshBotListAction;
import org.beuwi.msgbots.platform.app.view.actions.UpdateBotsPathAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.awt.*;
import java.io.File;

public class ChooseBotsPathDialog extends DialogWrap {
    private final ObservableMap<String, Object> namespace;
    private final FormLoader loader;
    private final VBox root;

    @FXML
    private TextField txfPath;
    @FXML private Button btnChooseDirectory;


    private final Button btnOK;
    private final Button btnCancel;

    private File file;
    private EventHandler<ChooseDirectoryEvent> onActionHandler;
    public ChooseBotsPathDialog() {
        loader = new FormLoader("dialog", "choose-directory-dialog", this);
        namespace = loader.getNamespace();
        root = loader.getRoot();

        btnOK = getActionButton();
        btnCancel = getCancelButton();

        btnOK.setText("OK");

        btnChooseDirectory.setOnAction(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose Directory");

            file = directoryChooser.showDialog(MainView.getStage());

            if (file != null) {
                txfPath.setText(file.getAbsolutePath());
            }
        });

        txfPath.textProperty().addListener(change -> {
            btnOK.setDisable(txfPath.getText().isEmpty());
        });
        String currentPath = SharedValues.BOTS_FOLDER_PATH;
        if(currentPath!=null) {
            txfPath.setText(currentPath);
        }
        Platform.runLater(() -> {
            txfPath.requestFocus();
        });
    }

    @Override
    public void open() {
        setContent(root);
        setTitle("Choose Bots Path");
        create();
    }

    @Override
    public void action() {

        if (txfPath.getText().isEmpty()) {
            return ;
        }
        String path = txfPath.getText();
        if(!(new File(path).isDirectory())){
            Toolkit.getDefaultToolkit().beep();

            return;
        }
        GlobalSettings.setData("program:bots_path", path);
        UpdateBotsPathAction.execute();
        if(onActionHandler!=null)
            onActionHandler.handle(new ChooseDirectoryEvent(path));
        close();
    }
    public final void setOnAction(EventHandler<ChooseDirectoryEvent> value) {
        onActionHandler = value;
    }

}