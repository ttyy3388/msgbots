package org.beuwi.msgbots.platform.app.view;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.platform.app.view.parts.DebugAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MainAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.MenuBarPart;
import org.beuwi.msgbots.platform.app.view.parts.SideAreaPart;
import org.beuwi.msgbots.platform.app.view.parts.StatusBarPart;
import org.beuwi.msgbots.platform.app.view.parts.ToastViewPart;
import org.beuwi.msgbots.platform.app.view.parts.ToolAreaPart;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class MainView extends StackPane implements View {
    private static MainView instance = null;

    private final ObservableMap<String, Object> namespace;
    private final FormLoader loader;

    @FXML private StackPane stpMenuBar;
    @FXML private StackPane stpInnerArea;
    @FXML private StackPane stpSideArea;
    @FXML private StackPane stpMainArea;
    @FXML private StackPane stpToolArea;
    @FXML private StackPane stpDebugArea;
    @FXML private StackPane stpStatusBar;

    private MainView() {
        loader = new FormLoader();
        loader.setName("main-view-layout");
        loader.setController(this);
        loader.setRoot(this);
        loader.load();

        namespace = loader.getNamespace();
        // root = loader.getRoot();

        // Add parts
        stpMenuBar.getChildren().add(MenuBarPart.getInstance());
        stpInnerArea.getChildren().add(ToastViewPart.getInstance());
        stpSideArea.getChildren().add(SideAreaPart.getInstance());
        stpMainArea.getChildren().add(MainAreaPart.getInstance());
        stpToolArea.getChildren().add(ToolAreaPart.getInstance());
        stpDebugArea.getChildren().add(DebugAreaPart.getInstance());
        stpStatusBar.getChildren().add(StatusBarPart.getInstance());
    }

    @Override
    public Object findById(String id) {
        return namespace.get(id);
    }

    public static void init() {
        if (instance != null) {
            throw new RuntimeException("already initialized");
        }
        instance = new MainView();
    }

    public static MainView getInstance() {
        if (instance == null) {
            throw new RuntimeException("not initialized");
        }
        return instance;
    }
}
