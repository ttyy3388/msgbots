package org.beuwi.msgbots.platform.gui.layout;

import javafx.geometry.Insets;

public class ShadowPane extends StackPane {

    private static final String DEFAULT_STYLE_CLASS = "shadow-pane";

    private static final int DEFAULT_PADDING_VALUE = 5;

	public ShadowPane() {
	    setPadding(new Insets(DEFAULT_PADDING_VALUE));
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	/* @Override
	public void setContent(Node content)
    {

    } */
}