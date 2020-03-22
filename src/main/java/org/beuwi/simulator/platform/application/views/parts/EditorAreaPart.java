package org.beuwi.simulator.platform.application.views.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class EditorAreaPart
{
	private static ObservableMap<String, Object> nameSpace;

	private static AnchorPane anpEditorArea;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(EditorAreaPart.class.getResource("/forms/EditorAreaPart.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anpEditorArea = (AnchorPane) nameSpace.get("anpEditorArea");
	}

	public static AnchorPane getRoot()
	{
		return anpEditorArea;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}