package org.beuwi.simulator.platform.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.managers.EditorAreaManager;
import org.beuwi.simulator.platform.ui.components.ITabPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorAreaController implements Initializable
{
    @FXML
    private AnchorPane anpEditorArea;

    // private ITabPane itapEditorArea;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		EditorAreaManager.initManager(anpEditorArea);
	}
}