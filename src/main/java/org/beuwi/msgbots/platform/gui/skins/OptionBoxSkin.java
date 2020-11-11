package org.beuwi.msgbots.platform.gui.skins;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.OptionBox;
import org.beuwi.msgbots.platform.gui.control.SkinBase;
import org.beuwi.msgbots.platform.gui.control.VBox;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

public class OptionBoxSkin extends SkinBase<OptionBox>
{
    private static final int DEFAULT_SPACING_VALUE = 10;

    private final Label title = new Label();
    private final StackPanel content = new StackPanel();

    private final VBox root = new VBox(title, content);

    {
        VBox.setVgrow(content, Priority.ALWAYS);
    }

    public OptionBoxSkin(OptionBox control)
    {
        super(control);

        title.setText(control.getTitle());
        content.setItem(control.getContent());

        title.addStyleClass("title");
        content.addStyleClass("content");
        content.setAlignment(Pos.CENTER_LEFT);

        root.setSpacing(DEFAULT_SPACING_VALUE);

        control.getTitleProperty().addListener(change ->
        {
            title.setText(getSkinnable().getTitle());
        });

        control.getContentProperty().addListener(change ->
        {
            content.setItem(getSkinnable().getContent());
        });

        this.setItem(root);
    }

    public void setTitle(String title)
    {
        this.title.setText(title);
    }

    public void setContent(Region content)
    {
        this.content.setItem(content);
    }

    public String getTitle()
    {
        return title.getText();
    }

    public Node getContent()
    {
        return content.getItem(0);
    }
}