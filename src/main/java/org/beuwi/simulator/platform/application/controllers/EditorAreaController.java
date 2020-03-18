package org.beuwi.simulator.platform.application.controllers;

import javafx.fxml.FXML;
import org.beuwi.simulator.platform.application.managers.EditorAreaManager;
import org.beuwi.simulator.platform.ui.components.ITabPane;

public class EditorAreaController
{
    @FXML
    private ITabPane itabPane;

    private EditorAreaManager model;

    public void initModel(EditorAreaManager model) throws IllegalAccessException
    {
        if (this.model != null)
        {
            throw new IllegalAccessException("Model can only be initialized once");
        }

        this.model = model;
    }
}