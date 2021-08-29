package org.beuwi.msgbots.program.gui.window;

import javafx.scene.Parent;
import javafx.scene.Scene;

import org.beuwi.msgbots.program.gui.layout.ShadowPane;

public class WindowScene extends Scene {
    public WindowScene(Parent root) {
        super(new ShadowPane(root));
    }
}
