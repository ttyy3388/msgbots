package org.beuwi.simulator.platform.application.views.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class GlobalLogTab
{
    private static ObservableMap<String, Object> nameSpace;

    private static AnchorPane root;

    private static ListView listView;
    private static TextArea textArea;
    private static Button button;

    public static void initialize() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DebugRoomTab.class.getResource("/forms/GlobalLogTab.fxml"));
        loader.setController(null);
        loader.load();

        nameSpace = loader.getNamespace();

        root = loader.getRoot();
    }

    public static AnchorPane getRoot()
    {
        return root;
    }

    public static ListView getComponent()
    {
        return (ListView) root.getChildren().get(0);
    }

    public static ObservableMap<String, Object> getNameSpace()
    {
        return nameSpace;
    }
}
