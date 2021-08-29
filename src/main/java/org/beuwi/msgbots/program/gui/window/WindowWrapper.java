package org.beuwi.msgbots.program.gui.window;

import javafx.stage.Stage;

public class WindowWrapper extends WindowFrame {
    protected WindowWrapper(Stage stage) {
        this(WindowType.WINDOW, stage);
    }
    protected WindowWrapper(WindowType type, Stage stage) {
        super(type, stage);
    }
}
