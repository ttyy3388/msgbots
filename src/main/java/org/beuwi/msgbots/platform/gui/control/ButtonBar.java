package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;

import java.util.List;
import java.util.stream.Collectors;

// @DefaultProperty("buttons")
public class ButtonBar extends HBox<Button> {
    private static final String DEFAULT_STYlE_CLASS = "button-bar";

    public ButtonBar() {
        setFittable(true);

        getStyleClass().add(DEFAULT_STYlE_CLASS);
    }

    public void setButtons(Button... buttons) {
        getItems().setAll(buttons);
    }

    public Button getButton(int index) {
        return getButtons().get(index);
    }

    public List<Button> getButtons() {
        return getChildren().stream().map(tab -> (Button) tab).collect(Collectors.toList());
    }
}