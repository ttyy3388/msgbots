package org.beuwi.msgbots.platform.app.view.dialogs;

import javafx.scene.Node;

import org.beuwi.msgbots.platform.gui.dialog.DialogWrap;

public class ShowNodeDialog extends DialogWrap {

    private final String title;
    private final Node content;

    public ShowNodeDialog(String title, Node content) {
        this.title = title;
        this.content = content;

        getButtonBar().setVisible(false);
    }

    @Override
    public void open() {
        setContent(content);
        setTitle(title);
        create();
    }

    @Override
    public void action() {

    }
}