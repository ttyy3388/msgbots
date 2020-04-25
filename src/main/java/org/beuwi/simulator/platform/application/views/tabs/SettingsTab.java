package org.beuwi.simulator.platform.application.views.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.utils.ResourceUtils;

public class SettingsTab
{
    private static ObservableMap<String, Object> nameSpace;

    private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("SettingsTab"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}

	public static TabPane getComponent()
    {
        return (TabPane) root.getChildren().get(0);
    }

	public static AnchorPane getRoot()
	{
		return root;
	}
}