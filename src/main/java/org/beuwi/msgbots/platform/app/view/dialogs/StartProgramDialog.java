package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.stage.DirectoryChooser;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.OpenFolderChooserAction;
import org.beuwi.msgbots.platform.app.view.actions.UpdateBotsPathAction;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.CheckBox;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.NaviItem;
import org.beuwi.msgbots.platform.gui.control.NaviView;
import org.beuwi.msgbots.platform.gui.control.TextField;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;

import java.awt.Toolkit;
import java.io.File;

public class StartProgramDialog extends DialogWrap {
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

    private final CheckBox chkFooter = new CheckBox();

    private final Button btnUpdate;
    private final Button btnCancel;

    private File saveDirFile;
    private File botDirFile;

    public StartProgramDialog() {
        loader = new FormLoader("dialog", "start-program-dialog", this);
        namespace = loader.getNamespace();
        root = loader.getRoot();

        btnUpdate = getActionButton();
        btnCancel = getCancelButton();

        btnUpdate.setText("Update");
        chkFooter.setText("시작 시 항상 이 다이얼로그 표시");

        txfBotDirPath.setText(SharedValues.getString("BOT_FOLDER_PATH"));
        txfSaveDirPath.setText(SharedValues.getString("SAVE_FOLDER_PATH"));

        chkFooter.setSelected(GlobalSettings.getBoolean("program:show_start_dialog"));

        btnChooseBotDir.setOnAction(event -> {
            botDirFile = OpenFolderChooserAction.execute();
            if (botDirFile != null) {
                txfBotDirPath.setText(botDirFile.getAbsolutePath());
            }
        });
        btnChooseSaveDir.setOnAction(event -> {
            saveDirFile = OpenFolderChooserAction.execute();
            if (saveDirFile != null) {
                txfSaveDirPath.setText(saveDirFile.getAbsolutePath());
            }
        });

        navConfigView.selectTab(0);
        getFooterArea().getChildren().add(chkFooter);
    }

    @Override
    protected void open() {
        setUseButton(true, false);
        setContent(root);
        setTitle("Welcome to Program");
        // setMargin(0);
    }

    @Override
    protected boolean action() {
        String botDirPath = txfBotDirPath.getText();
        String saveDirPath = txfSaveDirPath.getText();

        // 빈 칸이 나올 경우가 없긴 한데 혹시나 해서
        /* if (botDirPath == null ||
            saveDirPath == null) {
            return false;
        } */

        GlobalSettings.setData("program:show_start_dialog", chkFooter.isSelected());

        // 선택한 경로가 존재하지 않거나 폴더가 아닐 경우
        if (!(new File(botDirPath).isDirectory()) ||
            !(new File(saveDirPath).isDirectory())) {
            Toolkit.getDefaultToolkit().beep(); // 비프음 출력
            return false;
        }

        // 빈 칸이 나올 경우가 없긴 한데 혹시나 해서
        if (botDirPath != null) {
            GlobalSettings.setData("program:bot_dir_path", botDirPath);
            UpdateBotsPathAction.execute(botDirPath);
        }
        if (saveDirPath != null) {
            GlobalSettings.setData("program:save_dir_path", saveDirPath);
        }

        return true;
    }
}