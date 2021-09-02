package org.beuwi.msgbots.view.gui.dialog.base;

import javafx.stage.Stage;
import org.beuwi.msgbots.view.gui.type.DialogType;

public abstract class DialogWrapper extends DialogFrame {
    public DialogWrapper() {
        this(DialogType.NONE);
    }

    public DialogWrapper(DialogType type) {
        super(type, new Stage());
    }

    @Override
    protected abstract boolean onInit();
    @Override
    protected abstract boolean onOpen();
    @Override
    protected abstract boolean onAction();
    @Override
    protected abstract boolean onClose();
}
