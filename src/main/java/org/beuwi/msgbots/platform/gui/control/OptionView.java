package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import org.beuwi.msgbots.platform.gui.skins.OptionViewSkin;

public class OptionView extends Control
{
    private static final String DEFAULT_STYLE_CLASS = "option-view";

    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<Node> content = new SimpleObjectProperty();

    public OptionView()
    {
        addStyleClass(DEFAULT_STYLE_CLASS);
    }

    public void setTitle(String title)
    {
        this.title.set(title);
    }

    public void setContent(Node content)
    {
        this.content.set(content);
    }

    public String getTitle()
    {
        return title.get();
    }

    public Node getContent()
    {
        return content.get();
    }

    public StringProperty getTitleProperty()
    {
        return title;
    }

    public ObjectProperty<Node> getContentProperty()
    {
        return content;
    }

    @Override
    public OptionViewSkin setDefaultSkin()
    {
        return new OptionViewSkin(this);
    }
}
