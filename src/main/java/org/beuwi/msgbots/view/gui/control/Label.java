package org.beuwi.msgbots.view.gui.control;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.beuwi.msgbots.view.gui.control.base.LabelBase;

public class Label extends LabelBase {

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

	public void setText(Object object) {
		super.setText(object.toString());
	}
}