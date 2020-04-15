package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class StatusBarPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static Node root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StatusBarPart.class.getResource("/forms/StatusBarPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static Node getRoot()
	{
		return root;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}