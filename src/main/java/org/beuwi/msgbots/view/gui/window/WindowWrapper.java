package org.beuwi.msgbots.view.gui.window;

import javafx.stage.Stage;
import org.beuwi.msgbots.view.gui.type.WindowType;

public class WindowWrapper extends WindowFrame {
    protected WindowWrapper(Stage stage) {
        this(WindowType.WINDOW, stage);
    }
    protected WindowWrapper(WindowType type, Stage stage) {
        super(type, stage);
    }
}
