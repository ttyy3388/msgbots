package org.beuwi.msgbots.platform.gui.window;

import javafx.scene.Parent;
import javafx.scene.Scene;

import org.beuwi.msgbots.platform.gui.control.ShadowView;

public class WindowScene extends Scene {
    public WindowScene(final Parent root) {
        super(new ShadowView(root));
    }
}
