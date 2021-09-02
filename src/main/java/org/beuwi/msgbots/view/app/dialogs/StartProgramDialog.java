package org.beuwi.msgbots.view.app.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.setting.GlobalSettings;
import org.beuwi.msgbots.shared.SharedValues;
import org.beuwi.msgbots.utils.ResourceUtils;
import org.beuwi.msgbots.view.app.actions.OpenFolderChooserAction;
import org.beuwi.msgbots.view.gui.control.Button;
import org.beuwi.msgbots.view.gui.control.CheckBox;
import org.beuwi.msgbots.view.gui.control.NaviView;
import org.beuwi.msgbots.view.gui.control.TextField;
import org.beuwi.msgbots.view.gui.dialog.YesOrNoDialog;
import org.beuwi.msgbots.view.gui.layout.BorderPane;
import org.beuwi.msgbots.view.gui.layout.VBox;

import java.io.File;

public class StartProgramDialog extends YesOrNoDialog {
    private final ObservableMap<String, Object> namespace;
    private final FormLoader loader;
    private final VBox root;

    @FXML private NaviView navConfigView;
    // @FXML private NaviItem naiManualTab;
    // @FXML private NaviItem naiPresetTab;

    @FXML private TextField txfSaveDirPath;
    @FXML private Button btnChooseSaveDir;
    @FXML private TextField txfBotDirPath;
    @FXML private Button btnChooseBotDir;

    private final CheckBox chkShowStart = new CheckBox();

    private final Button btnUpdate;
    private final Button btnCancel;

    private File fileSaveDir;
    private File fileBotDir;

    // Image Size : 1323 X 756
    public StartProgramDialog() {
        loader = new FormLoader();
        loader.setName("start-program-dialog");
        loader.setController(this);
        loader.load();

        namespace = loader.getNamespace();
        root = loader.getRoot();

        btnUpdate = getActionButton();
        btnCancel = getCancelButton();

        btnUpdate.setText("Update");

        txfBotDirPath.setText(SharedValues.getString("path.botFolder"));
        txfSaveDirPath.setText(SharedValues.getString("path.saveFolder"));
        chkShowStart.setText("시작 시 항상 이 다이얼로그 표시");
        chkShowStart.setSelected(GlobalSettings.getBoolean("program.showStartDialog"));

        btnChooseBotDir.setOnAction(event -> {
            fileBotDir = OpenFolderChooserAction.getInstance().execute("Choose Bot Directory");
            if (fileBotDir != null) {
                txfBotDirPath.setText(fileBotDir.getAbsolutePath());
            }
        });
        btnChooseSaveDir.setOnAction(event -> {
            fileSaveDir = OpenFolderChooserAction.getInstance().execute("Choose Save Directory");
            if (fileSaveDir != null) {
                txfSaveDirPath.setText(fileSaveDir.getAbsolutePath());
            }
        });
    }

    @Override
    protected boolean onOpen() {
        setUseButton(true, false);
        setContent(root);
        setTitle("Welcome to Program");
        // setMargin(0);
        return true;
    }

    @Override
    protected boolean onInit() {
        return true;
    }

    @Override
    protected boolean onAction() {
        return true;
    }

    @Override
    protected boolean onClose() {
        return true;
    }
}
