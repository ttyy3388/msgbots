package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

// Navigation
public class Navi extends HBox
{
    private static final String DEFAULT_STYLE_CLASS = "navi";

    private static final Insets DEFAULT_PADDING_INSETS = new Insets(0, 10, 0, 10);

    private static final Pos DEFAULT_NAVI_POSITION = Pos.CENTER_LEFT;
    private static final int DEFAULT_MIN_HEIGHT = 30;

    private static final int DEFAULT_TITLE_SIZE = 50;

    private final ObjectProperty<Node> content = new SimpleObjectProperty<>(null);

    // Tab Title Label
    private final Label label = new Label();

    private NaviView control;

    {
        HBox.setHgrow(label, Priority.ALWAYS);
    }

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

        // label.setMinWidth(DEFAULT_TITLE_SIZE);
        // label.setAlignment(DEFAULT_NAVI_ALIGNMENT);
        // label.addStyleClass(TITLE_STYLE_CLASS);

        this.setOnMousePressed(event ->
        {
            getView().select(this);
        });

        setItem(label);
        setPadding(DEFAULT_PADDING_INSETS);
        // setSpacing(DEFAULT_TAB_SPACING);
        setMinHeight(DEFAULT_MIN_HEIGHT);
        setAlignment(DEFAULT_NAVI_POSITION);
        addStyleClass(DEFAULT_STYLE_CLASS);
    }

    public void setView(NaviView control)
    {
        this.control = control;
    }

    public void setText(String text)
    {
        label.setText(text);
    }

    public void setContent(Node content)
    {
        this.content.set(content);
    }

    public String getText()
    {
        return label.getText();
    }

    // Get Title Label
    public Label getLabel()
    {
        return label;
    }

    public HBox getHeader()
    {
        return this;
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
}
