package org.beuwi.msgbots.platform.util;

import javafx.beans.InvalidationListener;
import javafx.stage.Stage;

import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.gui.window.WindowEvent;

public class GlobalValues {
    public static void addEventHandler(WindowEvent event, InvalidationListener listener) {
        final Stage stage = MainView.getStage();

        switch (event) {
            case WINDOW_FOCUSED : stage.focusedProperty().addListener(listener); break;
            case WINDOW_SHOWING : stage.showingProperty().addListener(listener); break;
            // case WINDOW_HOVER -> ;
        }
    }
}