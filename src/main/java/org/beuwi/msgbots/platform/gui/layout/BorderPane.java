package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.NamedArg;
import javafx.scene.Node;

import org.beuwi.msgbots.platform.gui.base.Layout;

public class BorderPane extends javafx.scene.layout.BorderPane implements Layout {
    private static final String DEFAULT_STYLE_CLASS = "border-pane";

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

    public Node findById(String id) {
        return findById(this, id);
    };
}