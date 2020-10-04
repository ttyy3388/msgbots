package org.beuwi.msgbots.platform.gui.control;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.util.ResourceUtils;

// Log Item
public class Log extends ColorBox
{
	private static final String DEFAULT_STYLE_CLASS = "log-item";
	private static final double DEFAULT_MIN_HEIGHT = 70;


    // Log Text Label
    private final Label tlabel = new Label();

    // Log Date Label
    private final Label dlabel = new Label();

	private final AnchorPane pane = new AnchorPane(tlabel, dlabel);
	private final ImageView image = new ImageView();
	private final HBox root = new HBox(image, pane);

	private final Type type;

	public enum Type
	{
		ERROR
		{
			@Override
			public String toString()
			{
				return "error";
			}
		},

		EVENT
		{
			@Override
			public String toString()
			{
				return "event";
			}
		},

		DEBUG
		{
			@Override
			public String toString()
			{
				return "debug";
			}
		};
	}

	{
		HBox.setHgrow(pane, Priority.ALWAYS);

		AnchorPane.setTopAnchor(tlabel, 0.0);
		AnchorPane.setRightAnchor(tlabel, 0.0);
		AnchorPane.setLeftAnchor(tlabel, 0.0);

		AnchorPane.setRightAnchor(dlabel, 0.0);
		AnchorPane.setBottomAnchor(dlabel, 0.0);
		AnchorPane.setLeftAnchor(dlabel, 0.0);
	}

	public Log(String text, String date, Type type)
	{
		this.type = type;

		image.setImage(ResourceUtils.getImage(type.toString()));
		image.setFitWidth(20);
		image.setFitHeight(20);

		tlabel.setText(text);
		dlabel.setText(date);

		root.setSpacing(10);

		setType(type);
		setContent(root);
		setFillHeight(true);
		setMinHeight(DEFAULT_MIN_HEIGHT);
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

	public Type getType()
	{
		return type;
	}
}
