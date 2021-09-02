package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.NamedArg;
import javafx.scene.Node;

public class BorderPane extends javafx.scene.layout.BorderPane {
    {
        getStyleClass().add("border-pane");
    }

    public BorderPane() {
        super();
    }

    public BorderPane(@NamedArg("center") Node center) {
        setCenter(center);
    }

    public BorderPane(@NamedArg("top") Node top,
                      @NamedArg("right") Node right,
                      @NamedArg("bottom") Node bottom,
                      @NamedArg("left") Node left) {
        setTop(top);
        setRight(right);
        setBottom(bottom);
        setLeft(left);
    }

    public BorderPane(@NamedArg("top") Node top,
                      @NamedArg("right") Node right,
                      @NamedArg("center") Node center,
                      @NamedArg("bottom") Node bottom,
                      @NamedArg("left") Node left) {
        setTop(top);
        setRight(right);
        setCenter(center);
        setBottom(bottom);
        setLeft(left);
    }
}