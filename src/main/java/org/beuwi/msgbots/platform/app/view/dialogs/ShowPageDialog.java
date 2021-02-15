package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.scene.Node;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.dialog.DialogType;
import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class ShowPageDialog extends DialogWrap {
    private final StackPane root = new StackPane();

    private final String title;
    private final Node page;

    private final Button btnCopy;
    private final Button btnClose;

    public ShowPageDialog(String title, Node page) {
        super(DialogType.NONE);

        this.title = title;
        this.page = page;

        btnCopy = getActionButton();
        btnClose = getCancelButton();

        btnCopy.setText("");
        btnClose.setText("Close");

        root.getChildren().setAll(page);
    }

    @Override
    public void open() {
        setContent(root);
        setTitle(title);
        create();
    }

    @Override
    public void action() {
        close();
    }
}