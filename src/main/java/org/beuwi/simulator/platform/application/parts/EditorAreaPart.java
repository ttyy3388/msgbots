package org.beuwi.simulator.platform.application.parts;

import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class EditorAreaPart
{
	private static ObservableMap<String, Object> nameSpace;
	private static AnchorPane anchorPane;
	private static ITabPane tabPane;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(EditorAreaPart.class.getResource("/forms/EditorAreaForm.fxml"));
		loader.setController(null);
		loader.load();

		nameSpace = loader.getNamespace();

		anchorPane = (AnchorPane) nameSpace.get("anpEditorArea");
		tabPane = (ITabPane) nameSpace.get("tapEditorArea");
	}

	public static AnchorPane getRoot()
	{
		return anchorPane;
	}

	public static ITabPane getComponent()
	{
		return tabPane;
	}

	public static ObservableMap<String, Object> getNameSpace()
	{
		return nameSpace;
	}
}