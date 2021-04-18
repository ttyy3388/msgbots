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

    // Default : 400, 500
    public ShowPageDialog(String title, Node page) {
        this(title, page, 400, 500);
    }

    public ShowPageDialog(String title, Node page, double width, double height) {
        super(DialogType.NONE);

        this.title = title;
        this.page = page;

        root.setPrefWidth(width);
        root.setPrefHeight(width);

        root.getChildren().setAll(page);
    }

    @Override
    protected void open() {
        setContent(root);
        setTitle(title);
    }

    @Override
    protected boolean action() {
        return true;
    }
}