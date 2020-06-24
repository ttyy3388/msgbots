package org.beuwi.msgbots.platform.app.views.tabs;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class PreferencesTab
{
    private static ObservableMap<String, Object> nameSpace;

    private static StackPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("SettingsTab"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();

		Program.initialize();
		Debug.initialize();
		Bots.initialize();
	}

	public static class Program
	{
		public static void initialize()
		{

		}

		public static void apply()
		{

		}

		public static void refresh()
		{

		}
	}

	public static class Debug
	{
		public static void initialize()
		{

		}

		public static void apply()
		{

		}

		public static void refresh()
		{

		}
	}

	public static class Bots
	{
		public static void initialize()
		{

		}

		public static void apply()
		{

		}

		public static void refresh()
		{

		}
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}

	public static TabPane getComponent()
    {
        return (TabPane) root.getChildren().get(0);
    }

	public static StackPane getRoot()
	{
		return root;
	}
}