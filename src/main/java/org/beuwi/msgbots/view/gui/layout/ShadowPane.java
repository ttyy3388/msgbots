package org.beuwi.msgbots.view.gui.layout;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;

// 상속을 금지함 (StyleClass 등 처리해야할 게 많아지기 때문임)
public final class ShadowPane extends StackPane {

    // 굳이 ShadowArea 안에 ContentArea를 두는 이유는 ContentArea에 Shadow를 입히면 안 내용물에도 그림자가 생기기 때문임
    private final StackPane shadowArea = new StackPane();
    private final StackPane contentArea = new StackPane();

    public ShadowPane() {
        this(null);
    }

    public ShadowPane(Node content) {
        contentProperty().addListener(change -> {
            Node node = getContent();
            if (node != null) {
                contentArea.getChildren().setAll(node);
            }
        });

        if (content != null) {
            setContent(content);
        }

        // 창 띄울 때 흰 화면 방지
        setBackground(null);

        shadowArea.getChildren().add(contentArea);
        // contentArea.getStyleClass().add("root");
        shadowArea.getStyleClass().add("shadow-area");
        contentArea.getStyleClass().add("content-area");

        setPadding(new Insets(5));
        getChildren().setAll(shadowArea);
        getStyleClass().add("shadow-pane");
    }

    private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty(null);
    public final void setContent(Node content) {
        contentProperty.set(content);
    }
    public final Node getContent() {
        return contentProperty.get();
    }
    public final ObjectProperty<Node> contentProperty() {
        return contentProperty;
    }

    /* public final ObservableList<Node> getChildren() {
        return contentArea.getChildren();
    }
    public final ObservableList<String> getStyleClass() {
        return contentArea.getStyleClass();
    } */
}
