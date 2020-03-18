package org.beuwi.simulator.platform.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import org.beuwi.simulator.platform.application.actions.SelectActivityButtonAction;
import org.beuwi.simulator.platform.application.managers.ActivityBarManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ActivityBarController implements Initializable
{
    @FXML
    private AnchorPane anpActivityBar;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ActivityBarManager.initManager(anpActivityBar);

        ToggleButton btnProjects = (ToggleButton) anpActivityBar.getChildren().get(0);
        ToggleButton btnDebug = (ToggleButton) anpActivityBar.getChildren().get(1);

        btnProjects.setOnAction(event ->
        {
            SelectActivityButtonAction.update(0);
        });

        btnDebug.setOnAction(event ->
        {
            SelectActivityButtonAction.update(1);
        });
    }
}