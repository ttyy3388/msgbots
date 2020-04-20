package org.beuwi.simulator.platform.ui.components;

import javafx.scene.Node;
import org.fxmisc.flowless.VirtualizedScrollPane;

public class IScrollPane extends VirtualizedScrollPane
{
    {
        this.getStyleClass().add("i-scroll-pane");
    }

    public IScrollPane()
    {
        super(null);
    }

    public IScrollPane(Node node)
    {
        super(node);
    }
}