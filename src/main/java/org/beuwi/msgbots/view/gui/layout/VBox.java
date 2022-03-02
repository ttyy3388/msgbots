package org.beuwi.msgbots.view.gui.layout;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import org.beuwi.msgbots.view.gui.layout.base.VBoxBase;

public class VBox extends VBoxBase {
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

    // HBox 클래스 참고
    private final InvalidationListener fitContentListener = (observable -> {
        for (Node item : getChildren()) {
            if (item instanceof Region) {
                double value = getWidth();

                Insets margin = getMargin(VBox.this);
                Insets padding = getPadding();
                Insets border = null;
                if (getBorder() != null) {
                    border = getBorder().getInsets();
                }
                else {
                    // 어차피 0을 빼주면 똑같으므로 해당 방법 사용
                    border = new Insets(0, 0, 0, 0);
                }

                // 테두리 제거 너비
                if (border != null) {
                    value -= (border.getLeft() + border.getRight());
                }
                // 마진 제거 너비
                if (margin != null) {
                    value -= (margin.getLeft() + margin.getRight());
                }
                // 패딩 제거 너비
                if (padding != null) {
                    value -= (padding.getLeft() + padding.getRight());
                }

                ((Region) item).setPrefWidth(value);
            }
        }
    });

    public VBox() {
        this(null);
    }
    public VBox(Node... nodes) {
        if (nodes != null) {
            initChildren(nodes);
        }

        addChangeListener("fitContent", event-> {
            if (isFitContent()) {
                addChangeListener("width", fitContentListener);
            }
            else {
                removeChangeListener("width", fitContentListener);
            }
        });

        setFitContent(true); // Default
        // setOnMouseClicked();
        addStyleClass("vbox");
    }
}