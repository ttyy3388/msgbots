package org.beuwi.msgbots.view.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import org.beuwi.msgbots.view.gui.layout.StackPane;

// 상속을 금지함 (StyleClass 등 처리해야할 게 많아지기 때문임)
public final class ShadowView extends StackPane {
    private final StackPane contentArea = new StackPane();

    public ShadowView() {
        this(null);
    }

    public ShadowView(Node content) {
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

        // contentArea.getStyleClass().add("root");
        contentArea.getStyleClass().add("content-area");

        setPadding(new Insets(5));
        getChildren().setAll(contentArea);
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
