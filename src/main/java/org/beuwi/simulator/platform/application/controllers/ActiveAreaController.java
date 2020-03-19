package org.beuwi.simulator.platform.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.application.actions.ResizeSideBarAction;
import org.beuwi.simulator.platform.application.actions.SelectActivityButtonAction;
import org.beuwi.simulator.platform.application.managers.ActiveAreaManager;
import org.beuwi.simulator.platform.application.managers.ActivityBarManager;
import org.beuwi.simulator.platform.application.managers.SideBarManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ActiveAreaController implements Initializable
{
	@FXML
	private HBox hoxActiveArea;

	@FXML
	private StackPane stpResizeBar;

	@FXML
	private AnchorPane anpActivityBar;

	@FXML
	private AnchorPane anpSideBar;

	@FXML
	private ToggleButton tgnExplorerPart;

	@FXML
	private ToggleButton tgnSimulationPart;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ActiveAreaManager.initManager(hoxActiveArea);
		ActivityBarManager.initManager(anpActivityBar);
		SideBarManager.initManager(anpSideBar);

		tgnExplorerPart.setOnAction(event ->
		{
			SelectActivityButtonAction.update(0);
		});

		tgnSimulationPart.setOnAction(event ->
		{
			SelectActivityButtonAction.update(1);
		});

        stpResizeBar.setOnMouseDragged(event ->
        {
            ResizeSideBarAction.update(event);
        });
	}
}