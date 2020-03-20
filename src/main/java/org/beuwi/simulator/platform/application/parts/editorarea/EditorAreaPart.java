package org.beuwi.simulator.platform.application.parts.editorarea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class EditorAreaPart
{
	@FXML
	private AnchorPane anpEditorArea;

	@FXML
	private ITabPane tapEditorArea;

	public EditorAreaPart() throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/forms/EditorAreaForm.fxml"));
		loader.setController(this);
		loader.load();
	}
}