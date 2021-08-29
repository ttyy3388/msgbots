package org.beuwi.msgbots.program.gui.layout;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public class VBox extends javafx.scene.layout.VBox {
    private final BooleanProperty fittableProperty = new SimpleBooleanProperty();

    // 순수 너비만 가져오도록 "Margin, Padding" 요소를 제거한 너비를 가져옴
    /* 왜냐하면 부모 너비(P)는 '100'인데 패딩 '20'때문에 자식 너비가 '80'이라면
     * (P)100, (C)80 -> (C)100 -> (P)120 (C)100 -> (C)120... 무한반복
     */
    private final InvalidationListener fittableListener = new InvalidationListener() {
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

        fittableProperty().addListener(event-> {
            if (isFittable()) {
                widthProperty().addListener(fittableListener);
            }
            else {
                widthProperty().removeListener(fittableListener);
            }
        });

        setFittable(true); // Default
        // setOnMouseClicked();
        
        getStyleClass().add("vbox");
    }

    public void setFittable(boolean fittable) {
        this.fittableProperty.set(fittable);
    }
    public boolean isFittable() {
        return fittableProperty.get();
    }
    public BooleanProperty fittableProperty() {
        return fittableProperty;
    }
}