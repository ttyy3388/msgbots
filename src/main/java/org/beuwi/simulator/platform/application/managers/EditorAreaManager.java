package org.beuwi.simulator.platform.application.managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.beuwi.simulator.platform.ui.components.ITab;

public class EditorAreaManager
{
    private final ObservableList<ITab> itabList = FXCollections.observableArrayList();

    public final ITab getCurrentITab()
    {
        return null;
    }

    public ObservableList<ITab> getITabList()
    {
        return itabList;
    }
}