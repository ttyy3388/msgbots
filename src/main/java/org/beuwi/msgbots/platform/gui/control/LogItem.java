package org.beuwi.msgbots.platform.gui.control;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class LogItem extends StackPane
{
	private static final String DEFAULT_STYLE_CLASS = "log-item";
	private static final double DEFAULT_MIN_HEIGHT = 70;
	private static final double DEFAULT_PADDING = 10;

	private final AnchorPane pane = new AnchorPane();
	private final ImageView image = new ImageView();

	private final HBox hbox = new HBox();

	private final String type;

	// Log Text Label
	private final Label tlabel = new Label();

	// Log Date Label
	private final Label dlabel = new Label();

	{
		HBox.setHgrow(pane, Priority.ALWAYS);

		AnchorPane.setTopAnchor(tlabel, 0.0);
		AnchorPane.setRightAnchor(tlabel, 0.0);
		AnchorPane.setLeftAnchor(tlabel, 0.0);

		AnchorPane.setBottomAnchor(dlabel, 0.0);
		AnchorPane.setLeftAnchor(dlabel, 0.0);
	}

	public LogItem(String text, String date, String type)
	{
		this.type = type;

		image.setImage(ResourceUtils.getImage(type));
		image.setFitWidth(20);
		image.setFitHeight(20);

		tlabel.setText(text);
		dlabel.setText(date);

		pane.getChildren().addAll(tlabel, dlabel);

		hbox.setPadding(new Insets(DEFAULT_PADDING));
		hbox.setSpacing(10);
		hbox.setFillHeight(true);
		hbox.getStyleClass().add(type);
		hbox.getChildren().addAll(image, pane);

		setPrefHeight(DEFAULT_MIN_HEIGHT);
		getChildren().add(hbox);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public String getText()
	{
		return tlabel.getText();
	}

	public String getDate()
	{
		return dlabel.getText();
	}

	public String getType()
	{
		return type;
	}
}
