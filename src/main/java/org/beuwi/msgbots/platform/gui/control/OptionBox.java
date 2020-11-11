package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;
import org.beuwi.msgbots.platform.gui.skins.OptionBoxSkin;

// Option Box
public class OptionBox extends Control
{
    private static final String DEFAULT_STYLE_CLASS = "option-box";

    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<Node> content = new SimpleObjectProperty<>();

    public OptionBox()
    {
        addStyleClass(DEFAULT_STYLE_CLASS);

        content.addListener(event ->
        {
            if (getContent() instanceof Button)
            {
                Button control = (Button) getContent();
            }
            else if (getContent() instanceof CheckBox)
            {
                CheckBox control = (CheckBox) getContent();

                control.getSelectedProperty().addListener(change ->
                {

                });
            }
            else if (getContent() instanceof TextArea)
            {
                TextArea control = (TextArea) getContent();

                control.getTextProperty().addListener(change ->
                {

                });
            }
            else if (getContent() instanceof TextField)
            {
                TextField control = (TextField) getContent();

                control.getTextProperty().addListener(change ->
                {

                });
            }
            else if (getContent() instanceof ToggleButton)
            {
                ToggleButton control = (ToggleButton) getContent();

                control.getSelectedProperty().addListener(change ->
                {

                });
            }
            else if (getContent() instanceof ToggleSwitch)
            {
                ToggleSwitch control = (ToggleSwitch) getContent();

                control.getSelectedProperty().addListener(change ->
                {

                });
            }
        });
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
    public OptionBoxSkin setDefaultSkin()
    {
        return new OptionBoxSkin(this);
    }
}