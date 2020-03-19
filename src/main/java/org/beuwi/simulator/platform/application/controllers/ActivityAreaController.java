package org.beuwi.simulator.platform.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.actions.ResizeActivityAreaAction;
import org.beuwi.simulator.platform.application.managers.ActivityAreaManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityAreaController implements Initializable
{
	@FXML
	private AnchorPane anpActivityArea;

	@FXML
	private StackPane stpResizeBar;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ActivityAreaManager.initManager(anpActivityArea);

        stpResizeBar.setOnMouseDragged(event ->
        {
            ResizeActivityAreaAction.update(event);
        });
	}
}