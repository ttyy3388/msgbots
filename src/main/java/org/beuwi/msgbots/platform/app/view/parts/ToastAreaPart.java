package org.beuwi.msgbots.platform.app.view.parts;

import javafx.collections.ObservableMap;
import javafx.scene.layout.BorderPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.ToastView;

public class ToastAreaPart implements View
{
    private static ObservableMap<String, Object> nameSpace;

    private static FormLoader loader;

    private static BorderPane root;

    private static ToastView component;

    @Override
    public void init() throws Exception
    {
        loader = new FormLoader("toast-area-part");
        nameSpace = loader.getNamespace();
        root = loader.getRoot();
        component = loader.getComponent();
    }

    public static BorderPane getRoot()
    {
        return root;
    }

    public static ToastView getComponent()
    {
        return component;
    }

    public static ObservableMap<String, Object> getNameSpace()
    {
        return nameSpace;
    }
}
