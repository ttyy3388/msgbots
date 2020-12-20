package org.beuwi.msgbots.platform.gui.skins;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.Navi;
import org.beuwi.msgbots.platform.gui.control.OptionBox;
import org.beuwi.msgbots.platform.gui.control.OptionView;
import org.beuwi.msgbots.platform.gui.control.SkinBase;
import org.beuwi.msgbots.platform.gui.control.VBox;

public class OptionViewSkin extends SkinBase<OptionView>
{
    private static final int DEFAULT_SPACING_VALUE = 10;
    private static final int DEFAULT_HEADER_HEIGHT = 50;

    // private final StackPanel header = new StackPanel();

    private final Label title = new Label();
    private final VBox content = new VBox();

    private final VBox root = new VBox(title, content);

    {
        VBox.setVgrow(content, Priority.ALWAYS);
    }

    public OptionViewSkin(OptionView control)
    {
        super(control);

        title.setText(control.getTitle());
        title.addStyleClass("h2");

        content.setItems(control.getItems());
        content.setSpacing(30.0);
        content.addStyleClass("content");

        root.setSpacing(DEFAULT_SPACING_VALUE);
        title.setMinHeight(DEFAULT_HEADER_HEIGHT);

        control.getTitleProperty().addListener(change ->
        {
            title.setText(control.getTitle());

            /* if (title.getText().isEmpty())
            {
                root.remove(title);
            }
            else
            {
                if (!root.contains(title))
                {
                    root.addItem(0, title);
                }
            } */
        });

        control.getItems().addListener((ListChangeListener<Node>) change ->
        {
            content.setItems(control.getItems());
        });

        this.setItem(root);
    }

    public VBox getRoot()
    {
        return root;
    }
}