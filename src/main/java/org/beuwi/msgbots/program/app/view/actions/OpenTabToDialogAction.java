package org.beuwi.msgbots.program.app.view.actions;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

import org.beuwi.msgbots.program.app.impl.Executor;
import org.beuwi.msgbots.program.gui.dialog.ShowPaneDialog;
import org.beuwi.msgbots.program.gui.control.TabItem;
import org.beuwi.msgbots.program.gui.control.TabView;

public class OpenTabToDialogAction implements Executor {
    private static OpenTabToDialogAction instance = null;

    public void execute(TabItem item) {
        // 기존 탭뷰에서 삭제하고 다이얼 박스로 옮겨야 하기 때문에, 임시로 닫을 수 있게 만듬
        item.setClosable(true);

        TabView tabView = item.getView();
        tabView.closeTab(item);

        Node content = item.getContent();

        ShowPaneDialog dialog = new ShowPaneDialog() {
            @Override
            protected boolean onOpen() {
                return false;
            }

            @Override
            protected boolean onInit() {
                return false;
            }

            @Override
            protected boolean onAction() {
                return false;
            }

            @Override
            protected boolean onClose() {
                return false;
            }
        };

        dialog.setTitle(item.getText());
        dialog.setModality(Modality.NONE);
        dialog.setContent(content);
        dialog.setOnClose(event -> {
            ((Pane) content.getParent()).getChildren().remove(content);
            tabView.addTab(item);
            item.setContent(content);
            item.setClosable(false);
            tabView.selectTab(item);
        });

        OpenDialogBoxAction.getInstance().execute(dialog);
    }

    public static OpenTabToDialogAction getInstance() {
        if (instance == null) {
            instance = new OpenTabToDialogAction();
        }
        return instance;
    }
}
