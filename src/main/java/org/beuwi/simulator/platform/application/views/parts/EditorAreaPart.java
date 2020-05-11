package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.utils.ResourceUtils;

public class EditorAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static StackPane root;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("EditorAreaPart"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		root = loader.getRoot();
	}

	public static StackPane getRoot()
	{
		return root;
	}

	public static SplitPane getComponent()
	{
		return (SplitPane) root.getChildren().get(0);
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}