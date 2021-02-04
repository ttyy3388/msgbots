package org.beuwi.msgbots.platform.gui.control.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.beuwi.msgbots.platform.gui.base.Control;
import org.beuwi.msgbots.platform.gui.layout.StackPane;

public class TabItemBase extends StackPane implements Control {
    private static final String DEFAULT_STYLE_CLASS = "tab-item";
    // private static final String HEADER_STYLE_CLASS = "tab-header";

    public TabItemBase() {
        selectedProperty().addListener(change -> {
            if (getView() != null) {
                getView().selectTab(this);
            }
        });
        headerProperty().addListener(change -> {
            Node header = getHeader();
            // if (header.getStyleClass().indexOf(HEADER_STYLE_CLASS) != -1) {
                // header.getStyleClass().add(HEADER_STYLE_CLASS);
            // }
            getChildren().setAll(header);
        });
        addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            // 좌측 마우스 클릭 시
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (getView() != null) {
                    getView().selectTab(this);
                }
            }
        });
        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    private final ObjectProperty<Node> headerProperty = new SimpleObjectProperty(null);
    public void setHeader(Node header) {
        headerProperty.set(header);
    }
    public Node getHeader() {
        return headerProperty.get();
    }
    public ObjectProperty<Node> headerProperty() {
        return headerProperty;
    }

    private final ObjectProperty<TabViewBase> viewProperty = new SimpleObjectProperty(null);
    public void setView(TabViewBase view) {
        viewProperty.set(view);
    }
    public TabViewBase getView() {
        return viewProperty.get();
    }
    public ObjectProperty<TabViewBase> viewProperty() {
        return viewProperty;
    }

    private final BooleanProperty selectedProperty = new SimpleBooleanProperty(false);
    public void setSelected(boolean selected) {
        selectedProperty.set(selected);
    }
    public boolean isSelected() {
        return selectedProperty.get();
    }
    public BooleanProperty selectedProperty() {
        return selectedProperty;
    }

    private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty(null);
    public void setContent(Node content) {
        this.contentProperty.set(content);
    }
    public Node getContent() {
        return contentProperty.get();
    }
    public ObjectProperty<Node> contentProperty() {
        return contentProperty;
    }

    private final BooleanProperty closableProperty = new SimpleBooleanProperty(true);
    public void setClosable(boolean closable) {
        this.closableProperty.set(closable);
    }
    public boolean isClosable() {
        return closableProperty.get();
    }
    public BooleanProperty closableProperty() {
        return closableProperty;
    }
}
