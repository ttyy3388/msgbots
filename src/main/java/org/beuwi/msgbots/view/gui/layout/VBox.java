package org.beuwi.msgbots.view.gui.layout;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class VBox extends javafx.scene.layout.VBox {
    private final BooleanProperty fitChildProperty = new SimpleBooleanProperty();

    // HBox 클래스 참고
    private final InvalidationListener fitChildListener = new InvalidationListener() {
        @Override
        public void invalidated(Observable observable) {
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
        }
    };

    public VBox() {
        this(null);
    }
    public VBox(Node... nodes) {
        if (nodes != null) {
            getChildren().setAll(nodes);
        }

        fitChildProperty().addListener(event-> {
            if (isFitChild()) {
                widthProperty().addListener(fitChildListener);
            }
            else {
                widthProperty().removeListener(fitChildListener);
            }
        });

        setFitChild(true); // Default
        // setOnMouseClicked();
        getStyleClass().add("vbox");
    }

    public Node findById(String id) {
        for (Node node : getChildren()) {
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    public void setFitChild(boolean fitChild) {
        this.fitChildProperty.set(fitChild);
    }
    public boolean isFitChild() {
        return fitChildProperty.get();
    }
    public BooleanProperty fitChildProperty() {
        return fitChildProperty;
    }
}