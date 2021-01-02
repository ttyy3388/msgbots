package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;

public class Label extends javafx.scene.control.Label {
	private static final String DEFAULT_STYLE_CLASS = "label";

	{
		setMaxWidth(Double.MAX_VALUE);
		setMaxHeight(Double.MAX_VALUE);
		setAlignment(Pos.CENTER_LEFT); // 기본 정렬 : 중앙 좌측
		setTextAlignment(TextAlignment.LEFT);
		setContentDisplay(ContentDisplay.CENTER);
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