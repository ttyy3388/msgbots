package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.manager.LogManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Document;
import org.beuwi.msgbots.platform.gui.control.LogView;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.util.SharedValues;

public class WelcomeGuideTab implements View
{
    private static ObservableMap<String, Object> namespace;

    private static FormLoader loader;

    private static Tab root;

    private static Document component;

    @Override
    public void init()
    {
        loader = new FormLoader("tab", "program-guide-tab");
        namespace = loader.getNamespace();
        root = loader.getRoot();
        component = loader.getComponent();
    }

    public static Tab getRoot()
    {
        return root;
    }

    public static Document getComponent()
    {
        return component;
    }

    public static <T> T getComponent(String key)
    {
        return (T) namespace.get(key);
    }

    public static ObservableMap<String, Object> getNamespace()
    {
        return namespace;
    }
}