package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.Node;

import org.fxmisc.flowless.VirtualizedScrollPane;

public class ScrollPane extends VirtualizedScrollPane
{
    {
        getStyleClass().add("scroll-pane");
    }

    public ScrollPane()
    {
        this(null);
    }

    public ScrollPane(Node node)
    {
        super(node);
    }
}
