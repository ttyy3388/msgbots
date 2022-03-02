package org.beuwi.msgbots.view.gui.layout;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import org.beuwi.msgbots.view.gui.layout.base.HBoxBase;

public class HBox extends HBoxBase {
    private final BooleanProperty fitContentProperty = new SimpleBooleanProperty();
    public final BooleanProperty fitContentProperty() {
        return fitContentProperty;
    }
    public void setFitContent(boolean fitContent) {
        this.fitContentProperty.set(fitContent);
    }
    public boolean isFitContent() {
        return fitContentProperty.get();
    }

    /*
     * 순수 높이만 가져오도록 [Margin, Padding]요소를 제거한 높이를 가져온다.
     * 왜냐하면 부모 높이(P)는 '100'인데 패딩 '20'때문에 자식 높이(C)가 '80'이라면
     * (P)100, (C)80 -> (C)100 -> (P)120 (C)100 -> (C)120... 무한반복
     */
    private final InvalidationListener fitContentListener = (observable -> {
        for (Node item : getChildren()) {
            if (item instanceof Region) {
                double height = getHeight();

                Insets margin = getMargin(HBox.this);
                Insets padding = getPadding();
                Insets border = null;
                if (getBorder() != null) {
                    border = getBorder().getInsets();
                }
                else {
                    // 어차피 0을 빼주면 똑같으므로 해당 방법 사용
                    border = new Insets(0, 0, 0, 0);
                }

                // 테두리 제거 높이
                if (border != null) {
                    height -= (border.getTop() + border.getBottom());
                }
                // 마진 제거 높이
                if (margin != null) {
                    height -= (margin.getTop() + margin.getBottom());
                }
                // 패딩 제거 높이
                if (padding != null) {
                    height -= (padding.getTop() + padding.getBottom());
                }

                ((Region) item).setPrefHeight(height);
            }
        }
    });

    public HBox() {
        this(null);
    }
    public HBox(Node... nodes) {
        if (nodes != null) {
            initChildren(nodes);
        }

        addChangeListener("fitContent", change -> {
            if (isFitContent()) {
                addChangeListener("height", fitContentListener);
            }
            else {
                removeChangeListener("height", fitContentListener);
            }
        });

        setFitContent(true); // Default
        // setOnMouseClicked();
        addStyleClass("hbox");
    }

    @Override
    public Node findById(String id) {
        for (Node node : getChildren()) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }
}