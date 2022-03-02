package org.beuwi.msgbots.view.app.views;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.Node;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.base.impl.View;
import org.beuwi.msgbots.view.app.parts.DebugAreaPart;
import org.beuwi.msgbots.view.app.parts.MainAreaPart;
import org.beuwi.msgbots.view.app.parts.MenuBarPart;
import org.beuwi.msgbots.view.app.parts.SideAreaPart;
import org.beuwi.msgbots.view.app.parts.StatusBarPart;
import org.beuwi.msgbots.view.app.parts.ToastViewPart;
import org.beuwi.msgbots.view.app.parts.ToolAreaPart;
import org.beuwi.msgbots.view.gui.layout.StackPane;

public class MainView extends StackPane implements View {
    private static MainView instance = null;

    private final ObservableMap<String, Node> namespace;
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
        stpMenuBar.addChildren(MenuBarPart.getInstance());
        stpInnerArea.addChildren(ToastViewPart.getInstance());
        stpSideArea.addChildren(SideAreaPart.getInstance());
        stpMainArea.addChildren(MainAreaPart.getInstance());
        stpToolArea.addChildren(ToolAreaPart.getInstance());
        stpDebugArea.addChildren(DebugAreaPart.getInstance());
        stpStatusBar.addChildren(StatusBarPart.getInstance());
    }

    @Override
    public Node findById(String id) {
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
