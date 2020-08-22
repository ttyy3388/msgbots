package org.beuwi.msgbots.platform.app.view.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.app.impl.View;
import org.beuwi.msgbots.platform.gui.control.TabPane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class GlobalSettingTab implements View {

    private static ObservableMap<String, Object> nameSpace;

    private static StackPane root;

    @Override
    public void init() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ResourceUtils.getForm("global-setting-tab"));
        loader.setController(null);
        loader.load();

        nameSpace = loader.getNamespace();

        root = loader.getRoot();
    }

    public static StackPane getRoot()
    {
        return root;
    }

    public static TabPane getComponent()
    {
        return (TabPane) root.getChildren().get(0);
    }

    public static ObservableMap<String, Object> getNameSpace()
    {
        return nameSpace;
    }
}
