package org.beuwi.msgbots.view.gui.window;

import javafx.scene.Parent;
import javafx.scene.Scene;

import org.beuwi.msgbots.view.gui.layout.ShadowPane;

public class WindowScene extends Scene {
    public WindowScene(final Parent root) {
        super(new ShadowPane(root));
    }
}
