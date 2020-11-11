package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

// @DefaultProperties({@DefaultProperty("header"), @DefaultProperty("content")})
@DefaultProperty("items")
public class SplitView extends HBox<StackPanel>
{
    private static final String DEFAULT_STYLE_CLASS = "split-view";

    private final Header header = new Header();
    private final Content content = new Content();

    {
        HBox.setHgrow(content, Priority.ALWAYS);
    }

    public SplitView()
    {
        setItems(header, content);
    }

    public void setHeader(Header header)
    {
        this.header.setContent(header);
    }

    public void setContent(Content content)
    {
        this.content.setContent(content);
    }

    public Node getHeader()
    {
        return header.getContent();
    }

    public Node getContent()
    {
        return content.getContent();
    }

    @DefaultProperty("content")
    public static class Header extends StackPanel
    {
        public void setContent(Node pane)
        {
            setItem(pane);
        }

        public Node getContent()
        {
            return getItem(0);
        }
    }

    @DefaultProperty("content")
    public static class Content extends StackPanel
    {
        public void setContent(Node pane)
        {
            setItem(pane);
        }

        public Node getContent()
        {
            return getItem(0);
        }
    }
}