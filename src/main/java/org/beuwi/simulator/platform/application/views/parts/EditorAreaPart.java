package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class EditorAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(EditorAreaPart.class.getResource("/forms/EditorAreaPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static AnchorPane getRoot()
	{
		return root;
	}

	// Children get(0) : Component
	public static ITabPane getComponent()
	{
		return (ITabPane) root.getChildren().get(0);
	}

	/* public static TabPane getCurrentPane()
	{
		SplitPane pane = (SplitPane) root.getChildren().get(0);

		for (Node node : pane.getItems())
		{
			System.out.println(node + " : " + node.isFocused());
		}

		return null;
	} */

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}