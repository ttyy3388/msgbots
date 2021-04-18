package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.dialog.DialogType;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.BorderPane;
import org.beuwi.msgbots.platform.gui.layout.HBox;
import org.beuwi.msgbots.platform.gui.layout.VBox;

// 추후 개발 할 필요가 있어보여서 임시로 구현
// 다이얼 로그 박스 프레임에는 윈도우 자체만 구현해두고
// 이후 Yes/No 다이얼로그 버튼만 있는 다이얼로그를 구현
// 이 다이얼로그를 베이스를 해서 대다수의 다이얼로그를 구현
// EX : 아이콘을 포함해서 메시지를 출력하는 다이얼로그(Yes/No 포함)
public abstract class YesOrNoDialog extends DialogWrap {
    private final ObservableMap<String, Object> namespace;
    private final FormLoader loader;
    private final BorderPane root;

    // @FXML private BorderPane brpDialogRoot;
    @FXML private HBox<Button> hbxButtonBar;

    @FXML private Button btnAction;
    @FXML private Button btnCancel;

    public YesOrNoDialog() {
        this(DialogType.NONE);
    }

    public YesOrNoDialog(DialogType type) {
        this(type, false, false);
    }

    public YesOrNoDialog(DialogType type, boolean action, boolean cancel) {
        super(type);

        loader = new FormLoader("dialog", "yes-or-no-dialog", this);
        namespace = loader.getNamespace();
        root = loader.getRoot();

        /* ------------------------------------------------------ */

        btnAction.addEventHandler(ActionEvent.ACTION, event -> {
            this.daction();
        });
        btnCancel.addEventHandler(ActionEvent.ACTION, event -> {
            this.dclose();
        });

        getFooterArea().getChildren().add(hbxButtonBar);
    }

    @Override
    public void setContent(Pane content) {
        root.setCenter(content);
        super.setContent(root);
    }

    public HBox<Button> getButtonBar() {
        return hbxButtonBar;
    }

    public Button getActionButton() {
        return btnAction;
    }

    public Button getCancelButton() {
        return btnCancel;
    }

    // 액션(OK) 버튼 사용, 취소(NO) 버튼 사용
    public void setUseButton(boolean action, boolean cancel) {
        if (!action) {
            hbxButtonBar.getChildren().remove(btnAction);
        }
        if (!cancel) {
            hbxButtonBar.getChildren().remove(btnCancel);
        }
    }

    @Override
    protected abstract void open();

    @Override
    protected abstract boolean action();
}
