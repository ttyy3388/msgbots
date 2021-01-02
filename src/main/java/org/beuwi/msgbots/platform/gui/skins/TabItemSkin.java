package org.beuwi.msgbots.platform.gui.skins;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.control.Button;
import org.beuwi.msgbots.platform.gui.control.HBox;
import org.beuwi.msgbots.platform.gui.control.Label;
import org.beuwi.msgbots.platform.gui.control.TabItem;
import org.beuwi.msgbots.platform.util.AllSVGIcons;

public class TabItemSkin extends SkinBase<TabItem> {
    private static final String LABEL_STYLE_CLASS = "tab-label";
    private static final String BUTTON_STYLE_CLASS = "tab-button";

    private static final Insets DEFAULT_HEADER_PADDING = new Insets(0, 10, 0, 30);

    private static final Pos DEFAULT_HEADER_ALIGNMENT = Pos.CENTER;
    private static final int DEFAULT_HEADER_SPACING = 10;
    private static final int DEFAULT_HEADER_HEIGHT = 30;
    private static final int DEFAULT_HEADER_WIDTH = 100;

    private static final int DEFAULT_BUTTON_SIZE = 10;
    private static final int DEFAULT_TITLE_SIZE = 50;

    // Tab Close Button ( Action Button )
    private final Button button = new Button();
    // Tab Title Label
    private final Label label = new Label();
    private final HBox header = new HBox();

    {
        HBox.setHgrow(label, Priority.ALWAYS);
    }

    public TabItemSkin(final TabItem control) {
        super(control);

        // Initialize Values
        label.setPrefWidth(DEFAULT_TITLE_SIZE);
        label.setText(control.getText());
        label.getStyleClass().add(LABEL_STYLE_CLASS);

        button.setVisible(control.isClosable());
        button.setGraphic(AllSVGIcons.get("Tab.Close"));
        button.setOnAction(event -> {
            control.getView().removeTab(control);
        });
        button.setPrefWidth(DEFAULT_BUTTON_SIZE);
        button.getStyleClass().add(BUTTON_STYLE_CLASS);

        control.textProperty().addListener(change -> {
            label.setText(control.getText());
        });
        control.closableProperty().addListener(change -> {
            button.setVisible(control.isClosable());
        });
        control.focusedProperty().addListener(change -> {
        });

        header.setPrefWidth(DEFAULT_HEADER_WIDTH);
        header.setPrefHeight(DEFAULT_HEADER_HEIGHT);
        header.setSpacing(DEFAULT_HEADER_SPACING);
        header.setPadding(DEFAULT_HEADER_PADDING);
        header.setAlignment(DEFAULT_HEADER_ALIGNMENT);
        header.getChildren().setAll(label, button);

        this.getChildren().setAll(header);
    }
}
