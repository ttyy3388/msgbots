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

public class OptionBoxSkin extends SkinBase<OptionBox> {
    private static final int DEFAULT_SPACING_VALUE = 10;

    private final Label title = new Label();
    private final StackPanel content = new StackPanel();

    private final VBox root = new VBox(title, content);

    {
        VBox.setVgrow(content, Priority.ALWAYS);
    }

    public OptionBoxSkin(OptionBox control) {
        super(control);

        title.setText(control.getTitle());
        content.getItems().setAll(control.getContent());

        title.getStyleClass().add("title");
        content.getStyleClass().add("content");
        content.setAlignment(Pos.CENTER_LEFT);

        root.setSpacing(DEFAULT_SPACING_VALUE);

        control.titleProperty().addListener(change -> {
            title.setText(getSkinnable().getTitle());
        });

        control.contentProperty().addListener(change -> {
            content.getItems().setAll(getSkinnable().getContent());
        });

        getItems().setAll(root);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setContent(Region content) {
        this.content.getItems().setAll(content);
    }

    public String getTitle() {
        return title.getText();
    }

    public Node getContent() {
        return content.getItems().get(0);
    }
}