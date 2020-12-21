package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Label extends javafx.scene.control.Label {
	private static final String DEFAULT_STYLE_CLASS = "label";

	{
		setMaxWidth(Double.MAX_VALUE);
		setMaxHeight(Double.MAX_VALUE);
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

	public Label(ImageView image) {
		this.setGraphic(image);
	}

	public <T> void setText(T value) {
		super.setText("" + value);
	}
}