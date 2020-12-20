package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.skins.NaviSkin;

// Navigation
public class Navi extends Control
{
    private static final String DEFAULT_STYLE_CLASS = "navi";

    private final StringProperty text = new SimpleStringProperty(null);
    private final ObjectProperty<Node> content = new SimpleObjectProperty<>(null);

    private NaviView control;

    public Navi()
    {
        this(null);
    }

    public Navi(String title)
    {
        this(title, new Pane());
    }

    // 추후 사이드 옵션도 구현해야됨
    public Navi(String title, Node content)
    {
        if (title != null)
        {
            setId(title);
            setText(title);
        }

        if (content != null)
        {
            setContent(content);
        }

        this.setOnMousePressed(event ->
        {
            getView().selectNavi(this);
        });

        addStyleClass(DEFAULT_STYLE_CLASS);
    }

    public void setView(NaviView control)
    {
        this.control = control;
    }

    public void setText(String text)
    {
        this.text.set(text);
    }

    public void setContent(Node content)
    {
        this.content.set(content);
    }

    public String getText()
    {
        return text.get();
    }

    public Node getContent()
    {
        return content.get();
    }

    public NaviView getView()
    {
        return control;
    }

    public boolean isSelected()
    {
        return control.getSelectedNavi().equals(this);
    }

    public StringProperty getTextProperty()
    {
        return text;
    }

    public ObjectProperty<Node> getContentProperty()
    {
        return content;
    }

    @Override
    public Skin<?> setDefaultSkin()
    {
        return new NaviSkin(this);
    }
}
