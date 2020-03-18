package org.beuwi.simulator.platform.application.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SideBarController implements Initializable
{
    @FXML
    private AnchorPane anpSideBar;

    @FXML
    private AnchorPane anpBotPart;

    @FXML
    private AnchorPane anpDebugPart;

    @FXML
    private StackPane stpResizeBar;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}


