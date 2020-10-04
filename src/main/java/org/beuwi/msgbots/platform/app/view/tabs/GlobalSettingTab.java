package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.Tab;
import org.beuwi.msgbots.platform.gui.control.TabPane;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class GlobalSettingTab implements View
{
    private static ObservableMap<String, Object> nameSpace;

    private static FormLoader loader;

    private static Tab root;

    private static TabPane component;

    @Override
    public void init()
    {
        loader = new FormLoader("global-setting-tab");
        nameSpace = loader.getNamespace();
        root = loader.getRoot();
        component = (TabPane) ((VBox) ((StackPane) loader.getComponent()).getChildren().get(0)).getChildren().get(1);

        new OptionProgramTab().init();
        new OptionDebugTab().init();

        component.setSelectedTab(OptionProgramTab.getRoot());
    }

    public static class OptionProgramTab implements View
    {
        private static Tab root;

        @Override
        public void init()
        {
            root = component.getTab(0);
        }

        public static Tab getRoot()
        {
            return root;
        }
    }

    public static class OptionDebugTab implements View
    {
        private static Tab root;

        @Override
        public void init()
        {
            root = component.getTab(1);
        }

        public static Tab getRoot()
        {
            return root;
        }
    }

    public static Tab getRoot()
    {
        return root;
    }
}