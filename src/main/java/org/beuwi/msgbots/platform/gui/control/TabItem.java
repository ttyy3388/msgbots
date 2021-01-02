package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import org.beuwi.msgbots.platform.gui.skins.TabItemSkin;

public class TabItem extends Control {
    private static final String DEFAULT_STYLE_CLASS = "tab";

    private final ObjectProperty<Node> content = new SimpleObjectProperty(null);
    private final BooleanProperty closable = new SimpleBooleanProperty(true);
    // private final ObjectProperty<Type> type = new SimpleObjectProperty(null);
    private final StringProperty text = new SimpleStringProperty();

    // private final ObjectProperty<Type> type = new SimpleObjectProperty(null);

    private TabView parent;

    public TabItem() {
        this(null);
    }

    public TabItem(String text) {
        this(text, new Region());
    }

    public TabItem(String text, Node content) {
        if (text != null) {
            setText(text);
        }
        if (content != null) {
            setContent(content);
        }

        addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            // 탭 뷰가 지정돼지 않았을 경우가 없을 거 같긴 하지만 막아둠
            getView().selectTab(this);
        });

        getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    public void setView(TabView parent) {
        this.parent = parent;
    }

    public void setText(String text) {
        textProperty().set(text);
    }

    public void setContent(Node content) {
        contentProperty().set(content);
    }

    public void setClosable(boolean value) {
        closableProperty().set(value);
    }

    public TabView getView() {
        return parent;
    }

    public String getText() {
        return textProperty().get();
    }

    public Node getContent() {
        return contentProperty().get();
    }

    public boolean isClosable() {
        return closableProperty().get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public ObjectProperty<Node> contentProperty() {
        return content;
    }

    public BooleanProperty closableProperty() {
        return closable;
    }

    @Override
    public Skin<?> createDefaultSkin() {
        return new TabItemSkin(this);
    }
}
