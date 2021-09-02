package org.beuwi.msgbots.view.gui.window;

import javafx.scene.Parent;
import javafx.scene.Scene;

import org.beuwi.msgbots.view.gui.control.ShadowView;

public class WindowScene extends Scene {
    public WindowScene(final Parent root) {
        super(new ShadowView(root));
    }
}
