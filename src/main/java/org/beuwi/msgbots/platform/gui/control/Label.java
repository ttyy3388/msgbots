package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Label extends javafx.scene.control.Label {
	private static final String DEFAULT_STYLE_CLASS = "label";

	private static final double DEFAULT_PREF_HEIGHT = 20.0;

	{
		setMinHeight(DEFAULT_PREF_HEIGHT);
		setMaxWidth(Double.MAX_VALUE);
		setMaxHeight(Double.MAX_VALUE);
		setAlignment(Pos.CENTER_LEFT);
	}

	public Label() {
		super();
	}

    public Label(String text) {
        super(text);
    }

	public Label(Image image) {
		this(new ImageView(image));
	}

	/* public Label(Node node) {
		this.setGraphic(node);
	} */

	public Label(ImageView image) {
		this.setGraphic(image);
	}

	public <T> void setText(T value) {
		super.setText("" + value);
	}
}