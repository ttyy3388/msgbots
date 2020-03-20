package org.beuwi.simulator.platform.application.parts;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class EditorAreaPart
{
	private static AnchorPane anpEditorArea;
	private static ITabPane tapEditorArea;

	public static void initialize() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(EditorAreaPart.class.getResource("/forms/EditorAreaForm.fxml"));
		loader.setController(null);
		loader.load();

		anpEditorArea = (AnchorPane) loader.getNamespace().get("anpEditorArea");
		tapEditorArea = (ITabPane) loader.getNamespace().get("tapEditorArea");
	}

	public static AnchorPane getRoot()
	{
		return anpEditorArea;
	}

	public static ITabPane getComponent()
	{
		return tapEditorArea;
	}
}