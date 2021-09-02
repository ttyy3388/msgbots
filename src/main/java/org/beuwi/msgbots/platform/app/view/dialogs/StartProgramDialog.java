package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import org.beuwi.msgbots.platform.gui.dialog.ShowPaneDialog;
import org.beuwi.msgbots.platform.gui.layout.BorderPane;
import org.beuwi.msgbots.utils.ResourceUtils;

public class StartProgramDialog extends ShowPaneDialog {
    private final BorderPane root = new BorderPane();
    private final ImageView image = new ImageView(ResourceUtils.getImage("program_start"));

    // Image Size : 1323 X 756
    public StartProgramDialog() {
        image.setFitWidth(661.5);
        image.setFitHeight(378);

        // 모든 키 이벤트를 무시하도록 함
        // addEventFilter(KeyEvent.ANY, Event::consume);

        root.setCenter(image);
        // root.setBottom(progressBar);
        // root.setPrefWidth(661.5);
        // root.setPrefHeight(378);

        setUseTitleBar(false);
        setUseFooterBar(false);
    }

    /* public void update(int num) {
        progressBar.setProgress(num * 0.01); // 1%
    } */

    // 외부에서 콘텐츠 지정이 불가능 하도록
    @Override
    public void setContent(Node content) {
        return ;
    }
    @Override
    public ImageView getContent() {
        return image;
    }

    @Override
    protected boolean onOpen() {
        super.setContent(root);
        // 오픈 시 자동으로 사라지도록
        /* fade.setNode(this);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setCycleCount(5);
        fade.setAutoReverse(false);
        fade.play();
        fade.setOnFinished(event -> {
            dclose();
        }); */

        // 5초 뒤에 닫음
        new Thread(() -> {
            try { Thread.sleep(5000); }
            catch (Exception ignored) { }
            Platform.runLater(this::close);
        }).start();
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
